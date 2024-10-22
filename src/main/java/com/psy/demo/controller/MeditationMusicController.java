package com.psy.demo.controller;

import com.psy.demo.service.MeditationMusicService;
import com.psy.demo.vo.req.CommonTypeReq;
import com.psy.demo.vo.res.MeditationMusicTypeResFinalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public MeditationMusicTypeResFinalVO selectType(@RequestBody CommonTypeReq req) {
        return meditationMusicService.selectType(req);
    }

}
