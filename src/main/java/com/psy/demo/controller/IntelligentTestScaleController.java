package com.psy.demo.controller;

import com.psy.demo.service.IntelligentTestScaleService;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/intelligentTestScale")
public class IntelligentTestScaleController {

    @Autowired
    IntelligentTestScaleService intelligentTestScaleService;
    @GetMapping(path = "/select")
    public List<IntelligentTestScaleTypeResVO> select() {
        return intelligentTestScaleService.select();
    }

}
