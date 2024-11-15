package com.psy.demo.controller;

import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.service.PayInfoService;
import com.psy.demo.vo.req.FeedbackReq;
import com.psy.demo.vo.res.PayInfoFinalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payInfo")
public class PayInfoController {
    @Autowired
    PayInfoService payInfoService;


    @PostMapping(path = "/insert")
    public int insert(@RequestBody PayInfoDTO payInfoDTO) {
        return payInfoService.insert(payInfoDTO);
    }

    @GetMapping(path = "/select/{openId}")
    public List<PayInfoFinalVO> select(@PathVariable("openId") String openId) {
        return payInfoService.select(openId);
    }

    @PostMapping(path = "/dealFeedback")
    public int dealFeedback(@RequestBody FeedbackReq req) {
        return payInfoService.dealFeedback(req);
    }

    @GetMapping(path = "/selectByPsychologistId/{psychologistId}")
    public List<PayInfoFinalVO> selectByPsychologistId(@PathVariable("psychologistId") String psychologistId) {
        return payInfoService.selectByPsychologistId(psychologistId);
    }

}
