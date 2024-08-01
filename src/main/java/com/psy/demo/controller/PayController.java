package com.psy.demo.controller;

import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.service.PayInfoService;
import com.psy.demo.utils.GetTokenUtils;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.req.SignReq;
import com.psy.demo.vo.res.BaseRes;
import com.psy.demo.vo.res.PayCallbackRes;
import com.psy.demo.vo.res.PayRes;
import com.psy.demo.vo.res.SignRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    HttpClientService httpClientService;
    @Autowired
    PayInfoService payInfoService;

    @PostMapping(path = "/dealPrepay")
    public PayRes dealPost(@RequestBody PayReq payReq) {
        return httpClientService.dealPay(payReq);
    }

    @PostMapping(path = "/dealSign")
    public SignRes dealPost(@RequestBody SignReq signReq) {
        String nonceStr = UUID.randomUUID().toString();
        signReq.setNonceStr(nonceStr);
        String sign = GetTokenUtils.dealSign(signReq);
        SignRes res = new SignRes();
        res.setSign(sign);
        SignRes.genSignRes(res, signReq);
        return res;
    }

    @PostMapping(path = "/callback")
    public BaseRes dealCallback(@RequestBody PayCallbackRes payCallbackRes) {
        return httpClientService.dealCallback(payCallbackRes);
    }

}
