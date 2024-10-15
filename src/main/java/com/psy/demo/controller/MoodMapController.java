package com.psy.demo.controller;

import com.psy.demo.dto.MoodMapDTO;
import com.psy.demo.service.MoodMapService;
import com.psy.demo.vo.res.MoodMapResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moodMap")
public class MoodMapController {

    @Autowired
    MoodMapService moodMapService;

    @PostMapping(path = "/dealAdd")
    public int insert(@RequestBody MoodMapDTO moodMapDTO) {
        return moodMapService.dealAdd(moodMapDTO);
    }

    @GetMapping(path = "/selectByOpenId/{openId}")
    public List<MoodMapResVO> selectByOpenId(@PathVariable String openId) {
        return moodMapService.selectByOpenId(openId);
    }

}
