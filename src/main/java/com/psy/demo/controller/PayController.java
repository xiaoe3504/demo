package com.psy.demo.controller;

import com.psy.demo.service.HttpClientService;
import com.psy.demo.utils.GetTokenUtils;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.req.SignReq;
import com.psy.demo.vo.res.PayRes;
import com.psy.demo.vo.res.SignRes;
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

    @PostMapping(path = "/dealSign")
    public SignRes dealPost(@RequestBody SignReq signReq) {
        String sign = GetTokenUtils.dealSign(signReq);
        SignRes res = new SignRes();
        res.setSign(sign);
        return res;
    }


    @GetMapping(path = "/dealGet")
    public String dealGet() {
        return httpClientService.dealGet();
    }
}
