package com.psy.demo.service;


import com.psy.demo.vo.req.PayReq;

public interface HttpClientService {
    String dealPay(PayReq payReq);
    String dealGet();
}
