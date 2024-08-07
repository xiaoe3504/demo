package com.psy.demo.utils;

import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.vo.res.UserInfoRes;
import com.psy.demo.vo.res.UserInfoVO;
import org.jetbrains.annotations.NotNull;

public class MockUtil {
    public  static UserInfoRes mockUserInfoRes() {
        UserInfoRes res=new UserInfoRes();
        String encryptedData = "4ASmWgB+lW2FT/nVZzMOrWAjxdsag23qxCOiL3glxUogmBsHOP…AJHy7Yjl/lmzIlGTm/WvNilgj/v0JqLki3pNfGOuZ4wm9VmvU";
        String iv = "EI0QOmCyOWQhymUjaEIwLA==";
        String sessionKey = "VC5WkC07L7VdyXZF/n6K9g==";

        res.setEncryptedData(encryptedData);
        res.setIv(iv);
        res.setSessionKey(sessionKey);
        return res;
    }

    public static void main(String[] args) {
        // 示例参数
       /* UserInfoRes res=mockUserInfoRes();
        // 解密
        String userInfoJson = WeChatDecoder.decode(res.getSessionKey(), res.getEncryptedData(), res.getIv());
        // 打印解密后的用户信息（JSON 格式）
        System.out.println(userInfoJson);*/
    }

    @NotNull
    public static UserInfoVO mockUserInfoVO() {
        UserInfoVO userInfoVO = new UserInfoVO();
        String avatarUrl = MyConstantString.DEFAULT_AVATAR_URL;
        String nickName = MyConstantString.DEFAULT_NICK_NAME;
        userInfoVO.setAvatarUrl(avatarUrl);
        userInfoVO.setNickName(nickName);
        return userInfoVO;
    }

    @NotNull
    public static UserInfoDTO mockUserInfoDTO() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        String avatarUrl = MyConstantString.DEFAULT_AVATAR_URL;
        userInfoDTO.setAvatarUrl(avatarUrl);
        String nickName = MyConstantString.DEFAULT_NICK_NAME;
        userInfoDTO.setNickName(nickName);
//        String openId="mockedOpenId";
//        userInfoDTO.setOpenId(openId);
        return userInfoDTO;
    }

}
