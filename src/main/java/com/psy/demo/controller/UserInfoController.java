package com.psy.demo.controller;

import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.service.TestService;
import com.psy.demo.service.UserInfoService;
import com.psy.demo.utils.MockUtil;
import com.psy.demo.utils.WeChatDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;
    @GetMapping(path = "/add")
    public int saveUser() {
        UserInfoDTO userInfoDTO = MockUtil.mockUserInfoDTO();
        return userInfoService.saveUser(userInfoDTO);
    }

}
