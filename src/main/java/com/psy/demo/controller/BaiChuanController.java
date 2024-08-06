package com.psy.demo.controller;

import com.psy.demo.service.BaiChuanService;
import com.psy.demo.vo.req.baichuan.BaiChuanAddAndUpdateReq;
import com.psy.demo.vo.req.baichuan.BaiChuanChatReq;
import com.psy.demo.vo.req.baichuan.BaiChuanQueryReq;
import com.psy.demo.vo.res.baichuan.BaiChuanAddAndUpdateRes;
import com.psy.demo.vo.res.baichuan.BaiChuanChatRes;
import com.psy.demo.vo.res.baichuan.BaiChuanDeleteRes;
import com.psy.demo.vo.res.baichuan.BaiChuanQueryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baichuan")
public class BaiChuanController {
    @Autowired
    BaiChuanService baiChuanService;


    @PostMapping(path = "/dealAdd")
    public BaiChuanAddAndUpdateRes dealAdd(@RequestBody BaiChuanAddAndUpdateReq req) {
        return baiChuanService.dealAdd(req);
    }

    @PostMapping(path = "/dealQuery")
    public BaiChuanQueryRes dealPost(@RequestBody BaiChuanQueryReq baiChuanQueryReq) {
        return baiChuanService.dealQuery(baiChuanQueryReq);
    }

    @GetMapping(path = "/dealDelete/{id}")
    public BaiChuanDeleteRes dealDelete(@PathVariable int id) {
        return baiChuanService.dealDelete(id);
    }

    @PostMapping(path = "/dealUpdate/{id}")
    public BaiChuanAddAndUpdateRes dealUpdate(@PathVariable int id,@RequestBody BaiChuanAddAndUpdateReq req) {
        return baiChuanService.dealUpdate(req,id);
    }

}
