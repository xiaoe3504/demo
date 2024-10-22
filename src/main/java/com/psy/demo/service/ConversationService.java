package com.psy.demo.service;


import com.psy.demo.vo.req.ConversationReq;
import com.psy.demo.vo.res.MessageVO;

import java.util.List;

public interface ConversationService {

    int leaveMessage(ConversationReq req);

    List<MessageVO> getMessages(String openId);
}
