package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.service.LoginService;
import com.psy.demo.utils.TrustAllCerts;
import com.psy.demo.utils.WeChatDecoder;
import com.psy.demo.vo.res.FinalUserInfo;
import com.psy.demo.vo.res.LoginRes;
import com.psy.demo.vo.res.UserInfoRes;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Value("${miniProgram.loginUrl}")
    public String loginUrl;
    @Value("${miniProgram.appid}")
    public String appid;
    @Value("${miniProgram.secret}")
    public String secret;
    @Value("${miniProgram.grantType}")
    public String grantType;
    //url: 'https://api.weixin.qq.com/sns/jscode2session
    // ?appid=wxb1cd5bc64dbc5980&secret=850099790Sky!@#&js_code=' + res.code + '&grant_type=authorization_code',

    private static final int DEFAULT_TIME_OUT = 20;

    private OkHttpClient client = new OkHttpClient
            .Builder()
            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(8, 10, TimeUnit.SECONDS))
            //添加这两行代码
            .sslSocketFactory(TrustAllCerts.createSSLSocketFactory())
            .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier()).build();

    @Override
    public LoginRes login(String code) {
        String url = loginUrl + "?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grantType;
        Request request = new Request.Builder()
                .url(url)
                .build();
        log.info("login url:" + url);
        try {
            Response response = client.newCall(request).execute();
            String string = Objects.requireNonNull(response.body()).string();
            return JSONObject.parseObject(string, LoginRes.class);
        } catch (Exception e) {
            log.error("okhttp error:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public FinalUserInfo decodeUserInfo(UserInfoRes res) {
        String decode = WeChatDecoder.decode(res.getSessionKey(), res.getEncryptedData(), res.getIv());
        FinalUserInfo finalUserInfo = JSONObject.parseObject(decode, FinalUserInfo.class);
        return finalUserInfo;
    }
}
