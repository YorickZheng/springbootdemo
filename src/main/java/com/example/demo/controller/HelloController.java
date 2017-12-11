package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengyilong on 2017/11/30.
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String helloWord(){
        return  "<h1>hello world</h1>";
    }
}
