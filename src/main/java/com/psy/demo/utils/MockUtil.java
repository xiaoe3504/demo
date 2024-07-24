package com.psy.demo.utils;

import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.vo.res.UserInfoRes;
import com.psy.demo.vo.res.UserInfoVO;
import org.jetbrains.annotations.NotNull;

public class MockUtil {
    public static void mockUserInfoRes(UserInfoRes res) {
        String encryptedData = "7LoFF6oImPRRD/bUCZO6jdIADdYRP7KkflF9EQTticYtxfky5RGFm5BvwxmAI61grAfU0nF0qClEifrLoeuvSWpI7R2sJIwSSFr7lSe864l0b86S5j1bxLQDWBo/sxcRWa0cNeXu7UwbnGAtuY4s/l7yikf3J3LL52eW0tdbCyf/d//66+LHoDgbCFIf53xxIYkqCsjaNHd/Hdu4NBGp712fACpR28mwei4b5b1l2VfRcsL/hIRL/QhcugtyOoGY7hVF5a40zn+2GWaooVkRdOIpzKxG37RZ5TotPAPtUjm5z/0IXwj+oYKF4xtg/Ctvz4FXHcfqhSppuWIx2P5DLBwOUeOi9pM2fANG633CHHWlwDsWfaFZJV+mC5wEGUNOrnP+4Xg2Mq638Njnl1P6/9wSuZoagyWiWF+foCWOkv+EW8sn5cylqY+nbV1tvhCh+tAppih38xt0AYv4nIaV8Q==";
        res.setEncryptedData(encryptedData);
        String iv = "mF0iSde5NcylirMqugYIJw==";
        res.setIv(iv);
        String sessionKey = "MbPFdfreHEMk/hOU6Fa6eg==";
        res.setSessionKey(sessionKey);
        UserInfoVO userInfoVO = mockUserInfoVO();
        res.setUserInfoVO(userInfoVO);
    }

    @NotNull
    public static UserInfoVO mockUserInfoVO() {
        UserInfoVO userInfoVO = new UserInfoVO();
        String avatarUrl = "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
        userInfoVO.setAvatarUrl(avatarUrl);
        String nickName = "微信用户";
        userInfoVO.setNickName(nickName);
        return userInfoVO;
    }

    @NotNull
    public static UserInfoDTO mockUserInfoDTO() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        String avatarUrl = "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
        userInfoDTO.setAvatarUrl(avatarUrl);
        String nickName = "微信用户";
        userInfoDTO.setNickName(nickName);
//        String openId="mockedOpenId";
//        userInfoDTO.setOpenId(openId);
        return userInfoDTO;
    }

}
