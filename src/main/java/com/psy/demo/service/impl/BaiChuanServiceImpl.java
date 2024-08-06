package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.ext.SimpleHttpClient;
import com.psy.demo.service.BaiChuanService;
import com.psy.demo.utils.CommonUtils;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.vo.req.baichuan.BaiChuanAddAndUpdateReq;
import com.psy.demo.vo.req.baichuan.BaiChuanChatReq;
import com.psy.demo.vo.req.baichuan.BaiChuanQueryReq;
import com.psy.demo.vo.req.baichuan.CharacterProfile;
import com.psy.demo.vo.res.baichuan.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

import static com.psy.demo.utils.MyConstantString.*;


@Service
@Slf4j
public class BaiChuanServiceImpl implements BaiChuanService {
    @Value("${baichuan.url.addAndQuery}")
    public String urlAddAndQuery;
    @Value("${baichuan.url.deleteAndUpdate}")
    public String urlDeleteAndUpdate;
    @Value("${baichuan.url.chat}")
    public String urlChat;

    @Value("${baichuan.appKey}")
    public String appKey;

    @Autowired
    SimpleHttpClient simpleHttpClient;

    // 对话历史存储集合
    private static List<Message> historyList = new ArrayList<>();


    @NotNull
    private Map<String, String> getHeader() {
        Map<String, String> mapHeader = new HashMap<>();
        mapHeader.put("Authorization", appKey);
        return mapHeader;
    }

    public BaiChuanAddAndUpdateRes dealAdd(BaiChuanAddAndUpdateReq req) {
        SimpleHttpClient.ResponseReader reader = simpleHttpClient.doPost(urlAddAndQuery, req, getHeader());
        return reader.readObject(BaiChuanAddAndUpdateRes.class);
    }

    public BaiChuanQueryRes dealQuery(BaiChuanQueryReq req) {
        SimpleHttpClient.ResponseReader reader = simpleHttpClient.doGet(urlAddAndQuery, req, getHeader());
        return reader.readObject(BaiChuanQueryRes.class);
    }

    @Override
    public BaiChuanDeleteRes dealDelete(int id) {
        SimpleHttpClient.ResponseReader reader = simpleHttpClient.doDelete(urlDeleteAndUpdate, id, getHeader());
        return reader.readObject(BaiChuanDeleteRes.class);
    }

    @Override
    public BaiChuanAddAndUpdateRes dealUpdate(BaiChuanAddAndUpdateReq req, int id) {
        SimpleHttpClient.ResponseReader reader = simpleHttpClient.doPost(urlDeleteAndUpdate + id, req, getHeader());
        return reader.readObject(BaiChuanAddAndUpdateRes.class);
    }

    @Override
    public BaiChuanChatRes dealChat(BaiChuanChatReq req) {
        SimpleHttpClient.ResponseReader reader = simpleHttpClient.doPost(urlChat, req, getHeader());
        return reader.readObject(BaiChuanChatRes.class);
    }

    @Override
    public String dealMsg(String msg) {
        if (!CommonUtils.baiChuanCanAddHistory(historyList)) {
            IntStream.range(0, 3).map(i -> 0).forEach(historyList::remove);
        }
        Message message = Message.builder()
                .role(MyConstantString.BAICHUAN_ROLE_USER)
                .content(msg)
                .build();
        historyList.add(message);

        BaiChuanChatReq req = BaiChuanChatReq.builder()
                .model(BAICHUAN_MODEL)
                .character_profile(CharacterProfile.builder()
                        .character_id(BEICHUAN_CHARACTER_ID)
                        .build())
                .messages(historyList)
                .stream(false)
                .temperature(BAICHUAN_TEMPERATURE)
                .top_k(BAICHUAN_TOP_K)
                .max_tokens(BAICHUAN_MAX_TOKENS)
                .build();
        log.info("chat req:"+ JSONObject.toJSONString(req));
        SimpleHttpClient.ResponseReader reader = simpleHttpClient.doPost(urlChat, req, getHeader());
        BaiChuanChatRes res = reader.readObject(BaiChuanChatRes.class);
        log.info("chat res:"+ JSONObject.toJSONString(res));
        String answer = "";
        List<Choices> choices = res.getChoices();
        if (CollectionUtils.isNotEmpty(choices)) {
            Message message1 = choices.get(0).getMessage();
            if (message1 != null) {
                answer = message1.getContent();
            }
        }
        return answer;

    }


}
