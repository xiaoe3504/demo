package com.psy.demo.controller;

import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.service.PayInfoService;
import com.psy.demo.utils.GetTokenUtils;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.req.SignReq;
import com.psy.demo.vo.res.PayInfoVO;
import com.psy.demo.vo.res.PayRes;
import com.psy.demo.vo.res.SignRes;
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
}
