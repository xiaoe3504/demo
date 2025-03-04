package com.psy.demo.controller;

import com.psy.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping(path = "/dealAdd/{openId}")
    public int dealAdd(@PathVariable("openId") String openId) {
        return userInfoService.dealAdd(openId);
    }


    @GetMapping(path = "/dealIsMember/{openId}")
    public int dealIsMember(@PathVariable("openId") String openId) {
        return userInfoService.updateIsMemberByOpenId(openId);
    }

    @GetMapping(path = "/getHasReg/{openId}")
    public boolean getHasReg(@PathVariable("openId") String openId) {
        return userInfoService.getHasReg(openId);
    }

    @GetMapping(path = "/getIsMember/{openId}")
    public boolean getIsMember(@PathVariable("openId") String openId) {
        return userInfoService.getIsMember(openId);
    }

}
