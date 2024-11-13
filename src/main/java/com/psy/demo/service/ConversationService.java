package com.psy.demo.service;


import com.psy.demo.dto.MessageDTO;
import com.psy.demo.vo.req.ConversationReq;
import com.psy.demo.vo.req.MessageReq;
import com.psy.demo.vo.res.MessageVO;

import java.util.List;

public interface ConversationService {

    int leaveMessage(ConversationReq req);

    List<MessageVO> getMessages(MessageReq req);

    List<MessageVO> getMessagesPsychologist(MessageReq req);

    int dealHasRead(MessageDTO dto);

    int getUnreadCnt(MessageReq req);

}
