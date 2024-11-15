package com.psy.demo.service;


import com.psy.demo.vo.req.MyWithdrawReq;
import com.psy.demo.vo.res.WithdrawResVO;

public interface WithdrawService {


    WithdrawResVO dealWithdraw(MyWithdrawReq req);
}
