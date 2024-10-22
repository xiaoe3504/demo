package com.psy.demo.controller;

import com.psy.demo.dto.MeditationMusicDTO;
import com.psy.demo.service.IntelligentTestScaleService;
import com.psy.demo.service.MeditationMusicService;
import com.psy.demo.vo.req.CommonTypeReqVO;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResVO;
import com.psy.demo.vo.res.MeditationMusicTypeResFinalVO;
import com.psy.demo.vo.res.MeditationMusicTypeResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meditationMusic")
public class MeditationMusicController {

    @Autowired
    MeditationMusicService meditationMusicService;
    @GetMapping(path = "/select/{openId}")
    public MeditationMusicTypeResFinalVO select(@PathVariable String openId) {
        return meditationMusicService.select(openId);
    }

    @PostMapping(path = "/selectType")
    public MeditationMusicTypeResFinalVO selectType(@RequestBody CommonTypeReqVO req) {
        return meditationMusicService.selectType(req);
    }

}
