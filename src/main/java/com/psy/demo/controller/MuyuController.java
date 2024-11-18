package com.psy.demo.controller;

import com.psy.demo.dto.MuyuDTO;
import com.psy.demo.service.MuyuService;
import com.psy.demo.vo.res.MuyuCntVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/muyu")
public class MuyuController {

    @Autowired
    MuyuService muyuService;

    @GetMapping(path = "/insertOrUpdate/{openId}")
    public int insertOrUpdate(@PathVariable String openId) {
        return muyuService.insertOrUpdate(openId);
    }

    @GetMapping(path = "/getCnt/{openId}")
    public MuyuCntVO getCnt(@PathVariable String openId) {
        return muyuService.getCnt(openId);
    }


}
