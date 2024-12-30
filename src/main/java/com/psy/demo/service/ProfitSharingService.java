package com.psy.demo.service;


import com.google.gson.JsonObject;
import com.psy.demo.vo.req.MyProfitSharingQueryReq;
import com.psy.demo.vo.req.MyProfitSharingReq;
import com.psy.demo.vo.res.ProfitSharingAddRes;
import com.psy.demo.vo.res.ProfitSharingDealRes;

public interface ProfitSharingService {

    ProfitSharingDealRes dealProfitSharing(MyProfitSharingReq req);

    ProfitSharingAddRes addProfitSharing(String openId);

    JsonObject queryProfitSharing(MyProfitSharingQueryReq req);
}
