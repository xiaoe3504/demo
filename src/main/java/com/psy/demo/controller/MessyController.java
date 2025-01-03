package com.psy.demo.controller;

import com.psy.demo.utils.RegionUtils;
import com.psy.demo.vo.res.RegionVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messy")
public class MessyController {


    @GetMapping(path = "/region")
    public List<RegionVO> test() {
        return RegionUtils.getRegions();
    }

}
