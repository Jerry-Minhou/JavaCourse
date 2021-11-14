package com.rjkf.test.httpclienttest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hellotest")
    public String hello(){
        return "hello SpringBoot!";
    }
}
