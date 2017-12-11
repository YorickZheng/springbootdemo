package com.example.demo.controller;

import com.example.demo.entity.SpringBootEntity;
import com.example.demo.entity.model.ApiOutput;
import com.example.demo.repository.IMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhengyilong on 2017/12/1.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    IMongoRepository iMongoRepository;


    @RequestMapping(method = RequestMethod.POST,value = "/insert")
    public String insertValue(@RequestBody SpringBootEntity springBootEntity) {
        String res;
        try{
            iMongoRepository.save(springBootEntity);
            res = "成功";
        }catch (Exception ex){
            res = ex.getMessage();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getAll")
    public ApiOutput getList(){
        ApiOutput apiOutput;
        try {
            apiOutput = new ApiOutput(iMongoRepository.findAll());
        }catch (Exception ex) {
            ex.fillInStackTrace();
            apiOutput = new ApiOutput(ex.getMessage());
        }
        return  apiOutput;
    }


    @RequestMapping(method = RequestMethod.GET,value = "/getOne")
    public  ApiOutput getById(@RequestParam Long id){
        ApiOutput apiOutput;
        try{
            apiOutput = new ApiOutput(iMongoRepository.findOne(id));
        }catch (Exception ex){
            ex.fillInStackTrace();
            apiOutput = new ApiOutput(ex.getMessage());
        }
        return apiOutput;
    }
}
