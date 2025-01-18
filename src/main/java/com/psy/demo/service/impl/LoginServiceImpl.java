package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.global.BaseException;
import com.psy.demo.service.LoginService;
import com.psy.demo.utils.TrustAllCerts;
import com.psy.demo.utils.WeChatDecoder;
import com.psy.demo.vo.req.AccessTokenReq;
import com.psy.demo.vo.req.GetPhoneNumberReq;
import com.psy.demo.vo.res.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Value("${miniProgram.loginUrl}")
    public String loginUrl;
    @Value("${miniProgram.accessTokenUrl}")
    public String accessTokenUrl;
    @Value("${miniProgram.appid}")
    public String appid;
    @Value("${miniProgram.secret}")
    public String secret;
    @Value("${miniProgram.grantType}")
    public String grantType;
    @Value("${miniProgram.accessTokenGrantType}")
    public String accessTokenGrantType;
    @Value("${miniProgram.getPhoneNumberUrl}")
    public String getPhoneNumberUrl;

    //url: 'https://api.weixin.qq.com/sns/jscode2session
    // ?appid=wxb1cd5bc64dbc5980&secret=850099790Sky!@#&js_code=' + res.code + '&grant_type=authorization_code',

    private static final int DEFAULT_TIME_OUT = 10;

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

    public String getAccessToken() {
        String url = accessTokenUrl + "?appid=" + appid + "&secret=" + secret + "&grant_type=" + accessTokenGrantType;

        Request request = new Request.Builder()
                .url(url)
                .build();
        log.info("accessToken url:" + url);
        try {
            Response response = client.newCall(request).execute();
            String string = Objects.requireNonNull(response.body()).string();
            AccessTokenRes res = JSONObject.parseObject(string, AccessTokenRes.class);
            return res.getAccess_token();
        } catch (Exception e) {
            log.error("okHttp error:" + e.getMessage(), e);
        }
        return null;
    }

    public String dealGetPhoneNumber(String accessToken, String code) {
        String url = getPhoneNumberUrl + "?access_token=" + accessToken;
        GetPhoneNumberReq getPhoneNumberReq = GetPhoneNumberReq.builder().code(code).build();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSONObject.toJSONString(getPhoneNumberReq), mediaType);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        log.info("getPhoneNumber url:" + url);
        try {
            Response response = client.newCall(request).execute();
            String string = Objects.requireNonNull(response.body()).string();
            GetPhoneNumberRes res = JSONObject.parseObject(string, GetPhoneNumberRes.class);
            if (res.getErrcode() != 0) {
                log.error("getPhoneNumber err res:" + JSONObject.toJSONString(res));
                return null;
            } else {
                log.info("getPhoneNumber suc res:" + JSONObject.toJSONString(res));
                GetPhoneNumberPhoneInfo phone_info = res.getPhone_info();
                if (phone_info != null) {
                    return phone_info.getPhoneNumber();
                } else {
                    log.info("getPhoneNumber phone_info null:");
                    return null;
                }
            }


        } catch (Exception e) {
            log.error("okHttp error:" + e.getMessage(), e);
            throw new BaseException("okHttp error:");
        }
    }

    @Override
    public BaseRes getPhoneNumber(String code) {
        String accessToken = getAccessToken();
        String phoneNumber = dealGetPhoneNumber(accessToken, code);
        return BaseRes.ofSuccess(phoneNumber);
    }
}
