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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;


@Service
@Slf4j
public class WithdrawServiceImpl implements WithdrawService {
    @Autowired
    HttpClientService httpClientService;

    private static final String merchantId = MyConstantString.MERCHANT_ID; // 商户号
    private static final String appId = MyConstantString.APPID; // 商户号

    public static String privateKeyPath =
            "/Users/yourname/your/path/apiclient_key.pem";
    public static String merchantSerialNumber = MyConstantString.MERCHANT_SERIAL_NUMBER;
    /**
     * 商户证书序列号
     */
    public static String apiV3Key = MyConstantString.API_V3_KEY;

    /**
     * 商户APIV3密钥
     */


    @Override
    public WithdrawResVO dealWithdraw(MyWithdrawReq req) {
//        adjustParams(req);

        WithdrawReq withdrawReq = genWithdrawReq();
        System.out.println(JSONObject.toJSONString(withdrawReq));

        HttpPost httpPost = new HttpPost(MyConstantString.WITHDRAW_URL);
        httpPost.addHeader(ACCEPT, APPLICATION_JSON.toString());
        httpPost.addHeader(CONTENT_TYPE, APPLICATION_JSON.toString());

        String body = genBody(withdrawReq);
        log.info("withdraw body:" + body);
        StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        HttpUrl httpurl = HttpUrl.parse(MyConstantString.WITHDRAW_URL);
        String token = GetTokenUtils.getToken(HttpPost.METHOD_NAME, httpurl, body);
        System.out.println("token:" + token);


        return null;
    }

    private String genBody(WithdrawReq withdrawReq) {
        return "{" +
                "\"appid\": \"wxb1cd5bc64dbc5980\"," +
                "\"batch_name\": \"20241115测试转零钱\"," +
                "\"batch_remark\": \"20241115测试转零钱\"," +
                "\"out_batch_no\": \"test20241115test012336541\"," +
                "\"total_amount\": 1," +
                "\"total_num\": 1," +
                "\"transfer_detail_list\": [" +
                "{" +
                "\"openid\": \"o0Ya06wusUU-L8btHwi2BIAcj12U\"," +
                "\"out_detail_no\": \"test20241115test012336541001\"," +
                "\"transfer_amount\": 1," +
                "\"transfer_remark\": \"test20241115test012336541001\"" +
//                "\"user_name\": \"程远\"" +
                "}" +
                "]" +
                "}";
    }

    private WithdrawReq genWithdrawReq() {
        TransferDetailList inner = TransferDetailList.builder()
                .openid("o0Ya06wusUU-L8btHwi2BIAcj12U")
                .out_detail_no("test20241115_test012336541_001")
                .transfer_remark("test20241115_test012336541_001")
                .user_name("程远")
                .transfer_amount(1)
                .build();


        return WithdrawReq.builder()
                .appid(appId)
                .batch_name("20241115测试转零钱")
                .batch_remark("20241115测试转零钱")
                .out_batch_no("test20241115_test012336541")
                .total_amount(1)
                .total_num(1)
                .transfer_detail_list(
                        Arrays.asList(inner)
                )
                .build();
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
