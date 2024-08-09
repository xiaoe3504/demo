package com.psy.demo.controller;

import com.psy.demo.dto.UserInfoCategoryCntDTO;
import com.psy.demo.service.UserInfoCategoryCntService;
import com.psy.demo.vo.req.baichuan.BaiChuanAddAndUpdateReq;
import com.psy.demo.vo.req.baichuan.BaiChuanQueryReq;
import com.psy.demo.vo.res.baichuan.BaiChuanAddAndUpdateRes;
import com.psy.demo.vo.res.baichuan.BaiChuanDeleteRes;
import com.psy.demo.vo.res.baichuan.BaiChuanQueryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfoCnt")
public class UserInfoCategoryCntController {
    @Autowired
    UserInfoCategoryCntService userInfoCategoryCntService;


    @PostMapping(path = "/dealAdd")
    public int dealAdd(@RequestBody UserInfoCategoryCntDTO dto) {
        return userInfoCategoryCntService.insertOrUpdateCnt(dto);
    }

    @PostMapping(path = "/dealQuery")
    public int dealPost(@RequestBody UserInfoCategoryCntDTO dto) {
        return userInfoCategoryCntService.selectCntByUK(dto);
    }

}
