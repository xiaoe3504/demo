package com.psy.demo.service;


import com.psy.demo.vo.res.BaseRes;
import com.psy.demo.vo.res.FinalUserInfo;
import com.psy.demo.vo.res.LoginRes;
import com.psy.demo.vo.res.UserInfoRes;

public interface LoginService {
    LoginRes login(String code);

    FinalUserInfo decodeUserInfo(UserInfoRes res);

    String getAccessToken();

    BaseRes getPhoneNumber(String code);
}
