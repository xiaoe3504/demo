package com.psy.demo.vo.res;

import com.psy.demo.global.BaseException;
import com.psy.demo.vo.req.SignReq;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignRes {
    private String appId;
    private String timestamp;
    private String nonceStr;
    private String prepayIdStr;
    private String sign;

    public static SignRes genSignRes(SignRes res, SignReq req) {
        if (res == null) {
            throw new BaseException("genSignRes err");
        }
        res.setAppId(req.getAppId());
        res.setTimestamp(req.getTimestamp());
        res.setNonceStr(req.getNonceStr());
        res.setPrepayIdStr(req.getPrepayIdStr());
        return res;
    }
}
