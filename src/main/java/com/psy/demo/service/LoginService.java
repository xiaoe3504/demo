package com.psy.demo.service;


import com.psy.demo.vo.res.LoginRes;

public interface LoginService {
    LoginRes login(String code);
}
