package com.psy.demo.service.impl;

import com.psy.demo.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {


    @Override
    public String test() {
        return "test";
    }
}
