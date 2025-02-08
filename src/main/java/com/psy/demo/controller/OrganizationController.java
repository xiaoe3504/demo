package com.psy.demo.controller;

import com.psy.demo.dto.OrganizationDTO;
import com.psy.demo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @PostMapping(path = "/dealAdd")
    public int dealAdd(@RequestBody OrganizationDTO dto) {
        return organizationService.dealAdd(dto);
    }

    @GetMapping(path = "/getAll")
    public Map<String, String> getAll() {
        return organizationService.getMapOrg();
    }


}
