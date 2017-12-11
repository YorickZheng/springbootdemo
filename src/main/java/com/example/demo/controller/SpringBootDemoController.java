package com.example.demo.controller;

import com.example.demo.entity.SpringBootEntity;
import com.example.demo.entity.model.ApiOutput;
import com.example.demo.repository.ISpringBootDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

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
    public String get(@RequestParam Long id){

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

    @RequestMapping(method = RequestMethod.GET,value = "getAll")
    public ApiOutput getAll(){
        ApiOutput apiOutput;
        try{
            apiOutput = new ApiOutput(iSpringBootDemoRepository.findAll());
            apiOutput.setCode(0);
        }catch (Exception ex){
            ex.fillInStackTrace();
            apiOutput = new ApiOutput(ex.getMessage());
        }
        return apiOutput;
    }


}
