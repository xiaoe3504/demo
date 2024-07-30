package com.psy.demo.controller;

import com.psy.demo.dto.Note3DTO;
import com.psy.demo.service.Note3Service;
import com.psy.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note3")
public class Note3Controller {

    @Autowired
    Note3Service note3Service;

    @PostMapping(path = "/insert")
    public int insert(@RequestBody Note3DTO note3DTO) {
        return note3Service.insert(note3DTO);
    }

    @GetMapping(path = "/selectByOpenId/{openId}")
    public List<Note3DTO> selectByOpenId(@PathVariable String openId) {
        return note3Service.selectByOpenId(openId);
    }

}
