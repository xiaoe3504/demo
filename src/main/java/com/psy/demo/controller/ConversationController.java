package com.psy.demo.controller;

import com.psy.demo.service.ConversationService;
import com.psy.demo.vo.req.ConversationReq;
import com.psy.demo.vo.res.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation")
public class ConversationController {
    @Autowired
    ConversationService conversationService;

    @PostMapping(path = "/leaveMessage")
    public int leaveMessage(@RequestBody ConversationReq req) {
        return conversationService.leaveMessage(req);
    }

    @GetMapping(path = "/getMessages/{openId}")
    public List<MessageVO> getMessages(@PathVariable String openId) {
        return conversationService.getMessages(openId);
    }


}
