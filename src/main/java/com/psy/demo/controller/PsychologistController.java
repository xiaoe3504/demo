package com.psy.demo.controller;


import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.service.PsychologistService;
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
    public PsychologistDTO selectByOpenId(@PathVariable String openId) {
        return null;
    }

}
