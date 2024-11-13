package com.psy.demo.controller;

import com.psy.demo.dto.MessageDTO;
import com.psy.demo.service.ConversationService;
import com.psy.demo.vo.req.ConversationReq;
import com.psy.demo.vo.req.MessageReq;
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

    @PostMapping(path = "/getMessages")
    public List<MessageVO> getMessages(@RequestBody MessageReq req) {
        return conversationService.getMessages(req);
    }

    @PostMapping(path = "/getMessagesPsychologist")
    public List<MessageVO> getMessagesPsychologist(@RequestBody MessageReq req) {
        return conversationService.getMessagesPsychologist(req);
    }

    @PostMapping(path = "/getUnreadCnt")
    public int getHasReadCnt(@RequestBody MessageReq req) {
        return conversationService.getUnreadCnt(req);
    }

    @PostMapping(path = "/dealHasRead")
    public int dealHasRead(@RequestBody MessageDTO dto) {
        return conversationService.dealHasRead(dto);
    }


}
