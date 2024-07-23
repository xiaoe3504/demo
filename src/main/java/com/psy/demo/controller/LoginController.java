package com.psy.demo.controller;

import com.alibaba.fastjson.JSON;
import com.psy.demo.service.LoginService;
import com.psy.demo.service.TestService;
import com.psy.demo.vo.res.FinalUserInfo;
import com.psy.demo.vo.res.LoginRes;
import com.psy.demo.vo.res.UserInfo;
import com.psy.demo.vo.res.UserInfoRes;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(path = "login/{code}")
    public LoginRes test(@PathVariable("code") String code) {
        return loginService.login(code);
    }

    @PostMapping(path = "decode")
    public FinalUserInfo decodeUserInfo(@RequestBody UserInfoRes res) {
        log.info("res:" + JSON.toJSONString(res));
        return loginService.decodeUserInfo(res);

    }



}
