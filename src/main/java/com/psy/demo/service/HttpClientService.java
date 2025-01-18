package com.psy.demo.service;


import com.google.gson.JsonObject;
import com.psy.demo.vo.req.MyProfitSharingQueryReq;
import com.psy.demo.vo.req.MyProfitSharingReq;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.res.*;

public interface HttpClientService {
    PayRes dealPrepay(PayReq payReq);

    WeChatCerRes dealCer();

    WeChatCerRes getCerText();

    BaseRes dealCallback(PayCallbackRes payCallbackRes);

    ProfitSharingAddRes addProfitSharing(String  openId, String addUrl);

    ProfitSharingDealRes dealProfitSharing(MyProfitSharingReq req, String dealUrl);

    String queryTransactionId(String tradeNo);

    JsonObject queryProfitSharing(MyProfitSharingQueryReq req, String queryUrl);

    CozeAccessTokenRes getAccessToken();

    String createConversation(String cozeAccessToken);

    CozeBootRes chat(String conversationId, String openId, String content, String cozeAccessToken, String bootId);
}
