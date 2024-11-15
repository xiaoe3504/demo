package com.psy.demo.controller;

import com.psy.demo.service.WithdrawService;
import com.psy.demo.vo.req.MyWithdrawReq;
import com.psy.demo.vo.res.WithdrawResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/withdraw")
public class WithDrawController {
    @Autowired
    WithdrawService withdrawService;

    @PostMapping(path = "/dealWithdraw")
    public WithdrawResVO dealWithdraw(@RequestBody MyWithdrawReq req) {
        return withdrawService.dealWithdraw(req);
    }




}
