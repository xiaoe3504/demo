package com.psy.demo.controller;

import com.psy.demo.service.HttpClientService;
import com.psy.demo.vo.req.PayReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    HttpClientService httpClientService;

    @PostMapping(path = "/dealPay")
    public String dealPost(@RequestBody PayReq payReq) {
        return httpClientService.dealPay(payReq);
    }

    @GetMapping(path = "/dealGet")
    public String dealGet() {
        return httpClientService.dealGet();
    }
}
