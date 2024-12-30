package com.psy.demo.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.psy.demo.global.BaseException;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.service.WithdrawService;
import com.psy.demo.utils.GetTokenUtils;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.vo.req.MyWithdrawReq;
import com.psy.demo.vo.req.TransferDetailList;
import com.psy.demo.vo.req.WithdrawReq;
import com.psy.demo.vo.res.WithdrawResVO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;


@Service
@Slf4j
public class WithdrawServiceImpl implements WithdrawService {
    @Autowired
    HttpClientService httpClientService;


    @Override
    public WithdrawResVO dealWithdraw(MyWithdrawReq req) {


        return null;
    }




    public static void main(String[] args) {
        new WithdrawServiceImpl().dealWithdraw(null);
    }

    private void adjustParams(MyWithdrawReq req) {
        String openId = req.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId can not be null");
        }
        int amount = req.getAmount();
        if (amount <= 0) {
            throw new BaseException("amount invalid");
        }
    }
}
