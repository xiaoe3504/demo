package com.psy.demo.service.impl;


import com.google.gson.JsonObject;
import com.psy.demo.dto.ProfitSharingInfoDTO;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.PayInfoMapper;
import com.psy.demo.mapper.ProfitSharingInfoMapper;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.service.ProfitSharingService;
import com.psy.demo.utils.StringUtil;
import com.psy.demo.vo.req.MyProfitSharingQueryReq;
import com.psy.demo.vo.req.MyProfitSharingReq;
import com.psy.demo.vo.req.MyWithdrawReq;
import com.psy.demo.vo.res.ProfitSharingAddRes;
import com.psy.demo.vo.res.ProfitSharingDealRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ProfitSharingServiceImpl implements ProfitSharingService {
    @Autowired
    HttpClientService httpClientService;

    @Value("${miniProgram.profitSharing.addUrl}")
    public String addUrl;
    @Value("${miniProgram.profitSharing.dealUrl}")
    public String dealUrl;
    @Value("${miniProgram.profitSharing.queryUrl}")
    public String queryUrl;

    @Autowired
    ProfitSharingInfoMapper profitSharingInfoMapper;


    private void adjustParams(MyProfitSharingReq req) {
        String openId = req.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId can not be null");
        }
        String tradeNo = req.getTradeNo();
        if (StringUtils.isEmpty(tradeNo)) {
            throw new BaseException("tradeNo can not be null");
        }
        int amount = req.getAmount();
        if (amount <= 0) {
            throw new BaseException("amount invalid");
        }
    }

    @Override
    public ProfitSharingDealRes dealProfitSharing(MyProfitSharingReq req) {
        adjustParams(req);
        //添加分账方
        ProfitSharingAddRes addRes = addProfitSharing(req.getOpenId());
        if (addRes == null || StringUtils.isEmpty(addRes.getAccount())) {
            throw new BaseException("addProfitSharing error");
        }
        //查询微信支付订单号
        String transactionId = httpClientService.queryTransactionId(req.getTradeNo());
        if (StringUtils.isEmpty(transactionId)) {
            throw new BaseException("transactionId query error");
        }
        req.setTransactionId(transactionId);
        //将transactionId插入表
        dealInsertProfitSharing(req);
        //执行分账
        ProfitSharingDealRes res = httpClientService.dealProfitSharing(req, dealUrl);
        if (res == null || StringUtils.isEmpty(res.getOrder_id())) {
            throw new BaseException("dealProfitSharing error res null");
        }
        String outOrderNo = res.getOut_order_no();
        String orderId = res.getOrder_id();
        //更新 outOrderNo 和 orderId 方便后续查询和退款
        dealUpdateProfitSharing(req, outOrderNo, orderId);
        return res;
    }

    private void dealInsertProfitSharing(MyProfitSharingReq req) {
        ProfitSharingInfoDTO dto = new ProfitSharingInfoDTO();
        dto.setAmount(String.valueOf(req.getAmount()));
        dto.setTradeNo(req.getTradeNo());
        dto.setTransactionId(req.getTransactionId());
        int res = profitSharingInfoMapper.insert(dto);
        log.info("dealInsertProfitSharing res:" + res);
    }

    private void dealUpdateProfitSharing(MyProfitSharingReq req, String outOrderNo, String orderId) {
        ProfitSharingInfoDTO dto = new ProfitSharingInfoDTO();
        dto.setTradeNo(req.getTradeNo());
        dto.setOutOrderNo(outOrderNo);
        dto.setOrderId(orderId);
        int res = profitSharingInfoMapper.updateOutOrderNoOrderId(dto);
        log.info("dealUpdateProfitSharing res:" + res);
    }

    @Override
    public ProfitSharingAddRes addProfitSharing(String openId) {
        return httpClientService.addProfitSharing(openId, addUrl);
    }

    @Override
    public JsonObject queryProfitSharing(MyProfitSharingQueryReq req) {
        return httpClientService.queryProfitSharing(req, queryUrl);
    }
}
