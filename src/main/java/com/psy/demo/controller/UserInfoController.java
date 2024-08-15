package com.psy.demo.controller;

import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.service.TestService;
import com.psy.demo.service.UserInfoService;
import com.psy.demo.utils.MockUtil;
import com.psy.demo.utils.WeChatDecoder;
import com.psy.demo.vo.res.LoginRes;
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

}
