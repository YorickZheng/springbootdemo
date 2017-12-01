package com.example.demo.controller;

import com.example.demo.entity.SpringBootEntity;
import com.example.demo.repository.ISpringBootDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengyilong on 2017/11/30.
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/user")
public class SpringBootDemoController {

    @Autowired
    ISpringBootDemoRepository iSpringBootDemoRepository;

    @RequestMapping("/get")
    public String get(Long id){

        SpringBootEntity springBootEntity = iSpringBootDemoRepository.findOne(id);


        return springBootEntity.toString();
    }


    @RequestMapping(method = RequestMethod.POST,value = "create")
    public  String create(@RequestBody SpringBootEntity springBootEntity){
        String result;
        try {
            iSpringBootDemoRepository.save(springBootEntity);
            result = "成功";
        }catch (Exception ex){
            System.err.print(ex.getMessage());
            result = ex.getMessage();
        }
        return result;
    }


}
