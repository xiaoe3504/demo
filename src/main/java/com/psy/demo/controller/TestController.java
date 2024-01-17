package com.psy.demo.controller;

import com.psy.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testService;
    @GetMapping(path = "test")
    public String test() {
        return testService.test();
    }

}
