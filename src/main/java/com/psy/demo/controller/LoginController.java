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
    public FinalUserInfo decodeUserInfo(UserInfoRes res) {
        mockUserInfoRes(res);
        log.info("res:" + JSON.toJSONString(res));
        return loginService.decodeUserInfo(res);

    }

    public static void mockUserInfoRes(UserInfoRes res) {
        String encryptedData = "7LoFF6oImPRRD/bUCZO6jdIADdYRP7KkflF9EQTticYtxfky5RGFm5BvwxmAI61grAfU0nF0qClEifrLoeuvSWpI7R2sJIwSSFr7lSe864l0b86S5j1bxLQDWBo/sxcRWa0cNeXu7UwbnGAtuY4s/l7yikf3J3LL52eW0tdbCyf/d//66+LHoDgbCFIf53xxIYkqCsjaNHd/Hdu4NBGp712fACpR28mwei4b5b1l2VfRcsL/hIRL/QhcugtyOoGY7hVF5a40zn+2GWaooVkRdOIpzKxG37RZ5TotPAPtUjm5z/0IXwj+oYKF4xtg/Ctvz4FXHcfqhSppuWIx2P5DLBwOUeOi9pM2fANG633CHHWlwDsWfaFZJV+mC5wEGUNOrnP+4Xg2Mq638Njnl1P6/9wSuZoagyWiWF+foCWOkv+EW8sn5cylqY+nbV1tvhCh+tAppih38xt0AYv4nIaV8Q==";
        res.setEncryptedData(encryptedData);
        String iv = "mF0iSde5NcylirMqugYIJw==";
        res.setIv(iv);
        String sessionKey = "MbPFdfreHEMk/hOU6Fa6eg==";
        res.setSessionKey(sessionKey);
        UserInfo userInfo = mockUserInfo();
        res.setUserInfo(userInfo);
    }

    @NotNull
    private static  UserInfo mockUserInfo() {
        UserInfo userInfo = new UserInfo();
        String avatarUrl = "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
        userInfo.setAvatarUrl(avatarUrl);
        String nickName = "微信用户";
        userInfo.setNickName(nickName);
        return userInfo;
    }

}
