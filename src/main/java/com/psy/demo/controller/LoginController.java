package com.psy.demo.controller;

import com.psy.demo.service.LoginService;
import com.psy.demo.service.TestService;
import com.psy.demo.vo.res.LoginRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(path = "login/{code}")
    public LoginRes test(@PathVariable("code") String code) {
        return loginService.login(code);
    }

}
