package com.psy.demo.service;


import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.req.SignReq;
import com.psy.demo.vo.res.PayRes;

public interface HttpClientService {
    PayRes dealPay(PayReq payReq);

    String dealGet();

}
