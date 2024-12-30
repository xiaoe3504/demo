package com.psy.demo.controller;

import com.google.gson.JsonObject;
import com.psy.demo.service.ProfitSharingService;
import com.psy.demo.service.WithdrawService;
import com.psy.demo.vo.req.MyProfitSharingQueryReq;
import com.psy.demo.vo.req.MyProfitSharingReq;
import com.psy.demo.vo.req.MyWithdrawReq;
import com.psy.demo.vo.res.ProfitSharingDealRes;
import com.psy.demo.vo.res.WithdrawResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/profitSharing")
public class ProfitSharingController {
    @Autowired
    ProfitSharingService profitSharingService;

    @PostMapping(path = "/dealProfitSharing")
    public ProfitSharingDealRes dealProfitSharing(@RequestBody MyProfitSharingReq req) {
        return profitSharingService.dealProfitSharing(req);
    }

    @PostMapping(path = "/queryProfitSharing")
    public JsonObject queryProfitSharing(@RequestBody MyProfitSharingQueryReq req) {
        return profitSharingService.queryProfitSharing(req);
    }

}
