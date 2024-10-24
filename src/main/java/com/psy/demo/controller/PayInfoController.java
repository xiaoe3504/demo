package com.psy.demo.controller;

import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.service.PayInfoService;
import com.psy.demo.vo.res.PayInfoFinalVO;
import com.psy.demo.vo.res.PayInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payInfo")
public class PayInfoController {
    @Autowired
    PayInfoService payInfoService;


    @PostMapping(path = "/insert")
    public int insert(@RequestBody PayInfoDTO payInfoDTO) {
        return payInfoService.insert(payInfoDTO);
    }

    @PostMapping(path = "/select")
    public List<PayInfoVO> selectByOpenId(@RequestBody PayInfoDTO dto) {
        return payInfoService.selectByOpenId(dto);
    }

    @GetMapping(path = "/select/{openId}")
    public List<PayInfoFinalVO> select(@PathVariable("openId") String openId) {
        return payInfoService.select(openId);
    }
}
