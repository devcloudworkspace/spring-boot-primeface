package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping(path = "/")
    public String sayHi(){
        logger.info("inside sayHi");
        return "Hi";
    }

    @GetMapping(path = "/hello")
    public String sayHello(@RequestParam String name){
        logger.info("inside sayHello");
        return "Hello, "+name;
    }
}
