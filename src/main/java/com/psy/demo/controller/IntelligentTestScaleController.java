package com.psy.demo.controller;

import com.psy.demo.service.IntelligentTestScaleService;
import com.psy.demo.vo.req.CommonTypeReqVO;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResFinalVO;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intelligentTestScale")
public class IntelligentTestScaleController {

    @Autowired
    IntelligentTestScaleService intelligentTestScaleService;
    @GetMapping(path = "/select/{openId}")
    public IntelligentTestScaleTypeResFinalVO select(@PathVariable String openId) {
        return intelligentTestScaleService.select(openId);
    }

    @PostMapping(path = "/selectType")
    public IntelligentTestScaleTypeResFinalVO selectType(@RequestBody CommonTypeReqVO req) {
        return intelligentTestScaleService.selectType(req);
    }

}
