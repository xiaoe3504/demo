package com.psy.demo.service;


import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.res.BaseRes;
import com.psy.demo.vo.res.PayCallbackRes;
import com.psy.demo.vo.res.PayRes;
import com.psy.demo.vo.res.WeChatCerRes;

public interface HttpClientService {
    PayRes dealPrepay(PayReq payReq);

    WeChatCerRes dealCer();

    WeChatCerRes getCerText();

    BaseRes dealCallback(PayCallbackRes payCallbackRes);

    void dealWithDraw();

}
