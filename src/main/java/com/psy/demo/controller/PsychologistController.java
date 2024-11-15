package com.psy.demo.controller;


import com.psy.demo.dto.MessageDTO;
import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.service.PsychologistService;
import com.psy.demo.vo.req.MessageReq;
import com.psy.demo.vo.res.OrderAmountVO;
import com.psy.demo.vo.res.PsychologistOrderResVO;
import com.psy.demo.vo.res.PsychologistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/psychologist")
public class PsychologistController {

    @Autowired
    PsychologistService psychologistService;


    @PostMapping(path = "/dealAdd")
    public int insert(@RequestBody PsychologistDTO psychologistDTO) {
        return psychologistService.dealAdd(psychologistDTO);
    }

    @GetMapping(path = "/selectAll")
    public List<PsychologistVO> selectAll() {
        return psychologistService.selectAll();
    }

    @GetMapping(path = "/select/{openId}")
    public PsychologistOrderResVO selectOrderResByOpenId(@PathVariable String openId) {
        return psychologistService.selectOrderResByOpenId(openId);
    }

    @GetMapping(path = "/getOrderCntAndAmount/{psychologistId}")
    public OrderAmountVO getOrderCntAndAmount(@PathVariable("psychologistId") String psychologistId) {
        return psychologistService.getOrderCntAndAmount(psychologistId);
    }

    @PostMapping(path = "/setStatus")
    public int setStatus(@RequestBody PsychologistDTO dto) {
        return psychologistService.setStatus(dto);
    }

    @PostMapping(path = "/getUnreadCnt")
    public int getHasReadCnt(@RequestBody MessageReq req) {
        return psychologistService.getUnreadCnt(req);
    }

    @GetMapping(path = "/isPsychologist/{openId}")
    public boolean isPsychologist(@PathVariable String openId) {
        return psychologistService.isPsychologist(openId);
    }


}
