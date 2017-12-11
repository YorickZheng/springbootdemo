package com.example.demo.controller;

import com.example.demo.entity.SpringBootEntity;
import com.example.demo.entity.model.ApiOutput;
import com.example.demo.enums.ApiCodeEnum;
import com.example.demo.repository.IMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengyilong on 2017/12/4.
 */
@RestController
@RequestMapping("/security")
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableAutoConfiguration
public class SecurityController extends WebSecurityConfigurerAdapter {

    @Autowired
    IMongoRepository iMongoRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public ApiOutput getAll() {
        ApiOutput apiOutput = new ApiOutput();
        try {
            apiOutput.setCode(ApiCodeEnum.SUCCESS.getCode());
            apiOutput.setData(iMongoRepository.findAll());
        } catch (Exception ex) {
            apiOutput.setCode(ApiCodeEnum.FAIL.getCode());
            apiOutput.setData(ex.getMessage());
        }
        return apiOutput;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @Secured("ROLE_ADMIN")
    public ApiOutput create(@RequestBody SpringBootEntity springBootEntity) {
        ApiOutput apiOutput = new ApiOutput();
        try {
            iMongoRepository.save(springBootEntity);
            apiOutput.setCode(ApiCodeEnum.SUCCESS.getCode());
            apiOutput.setMsg("成功");
        } catch (Exception ex) {
            apiOutput.setCode(ApiCodeEnum.FAIL.getCode());
            apiOutput.setMsg(ex.getMessage());
        }
        return apiOutput;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("root").password("foo").roles("USER")
                .and().withUser("admin").password("password").roles("USER","ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("SpringBoot");
        httpSecurity.exceptionHandling().authenticationEntryPoint(entryPoint);
        httpSecurity.requestMatchers().antMatchers("/**").anyRequest()
                .and().httpBasic()
                .and().anonymous().disable().csrf().disable();
    }
}
