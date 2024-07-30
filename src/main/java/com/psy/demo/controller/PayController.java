package com.psy.demo.controller;

import com.psy.demo.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    HttpClientService httpClientService;

    @GetMapping(path = "/dealPost")
    public String dealPost() {
        String openId = "o0Ya06wusUU-L8btHwi2BIAcj12U";
        return httpClientService.dealPost(openId);
    }

    @GetMapping(path = "/dealGet")
    public String dealGet() {
        return httpClientService.dealGet();
    }
}
