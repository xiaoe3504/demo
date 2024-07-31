package com.psy.demo.controller;

import com.psy.demo.service.HttpClientService;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.res.PayRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    HttpClientService httpClientService;

    @PostMapping(path = "/dealPay")
    public PayRes dealPost(@RequestBody PayReq payReq) {
        return httpClientService.dealPay(payReq);
    }

    @GetMapping(path = "/dealGet")
    public String dealGet() {
        return httpClientService.dealGet();
    }
}
