package com.psy.demo.controller;

import com.psy.demo.service.GrowthCenterService;
import com.psy.demo.vo.res.GrowthCenterInnerVO;
import com.psy.demo.vo.res.GrowthCenterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/growthCenter")
public class GrowthCenterController {

    @Autowired
    GrowthCenterService growthCenterService;

    @GetMapping(path = "/queryByType/{type}")
    public GrowthCenterVO queryByType(@PathVariable("type") String type) {
        return growthCenterService.queryByType(type);
    }


}
