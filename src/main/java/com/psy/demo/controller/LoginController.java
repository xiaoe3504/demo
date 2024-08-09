package com.psy.demo.controller;

import com.alibaba.fastjson.JSON;
import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.service.LoginService;
import com.psy.demo.service.UserInfoService;
import com.psy.demo.utils.CommonUtils;
import com.psy.demo.vo.res.FinalUserInfo;
import com.psy.demo.vo.res.LoginRes;
import com.psy.demo.vo.res.UserInfoRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    UserInfoService userInfoService;

    @GetMapping(path = "login/{code}")
    public LoginRes test(@PathVariable("code") String code) {
        return loginService.login(code);
    }

    @PostMapping(path = "decodeAndSave")
    public UserInfoDTO decodeUserInfo(@RequestBody UserInfoRes userInfoRes) {
        log.info("userInfoRes:" + JSON.toJSONString(userInfoRes));
        FinalUserInfo finalUserInfo = loginService.decodeUserInfo(userInfoRes);
        UserInfoDTO userInfoDTO = CommonUtils.genUserInfoDTO(finalUserInfo);
        userInfoService.saveUser(userInfoDTO);
        return userInfoDTO;

    }

    @PostMapping(path = "/saveOrUpdateNickname")
    public int saveOrUpdateNickname(@RequestBody UserInfoDTO userInfoDTO) {
        log.info("userInfoRes:" + JSON.toJSONString(userInfoDTO));
       return userInfoService.saveOrUpdateNickname(userInfoDTO);

    }

    @PostMapping(path = "/getDTOByOpenId")
    public UserInfoDTO getDTOByOpenId(@RequestBody UserInfoDTO userInfoDTO) {
        return userInfoService.getDTOByOpenId(userInfoDTO);
    }

}
