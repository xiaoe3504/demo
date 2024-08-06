package com.psy.demo.service;


import com.psy.demo.vo.req.baichuan.BaiChuanAddAndUpdateReq;
import com.psy.demo.vo.req.baichuan.BaiChuanChatReq;
import com.psy.demo.vo.req.baichuan.BaiChuanQueryReq;
import com.psy.demo.vo.res.baichuan.BaiChuanAddAndUpdateRes;
import com.psy.demo.vo.res.baichuan.BaiChuanChatRes;
import com.psy.demo.vo.res.baichuan.BaiChuanDeleteRes;
import com.psy.demo.vo.res.baichuan.BaiChuanQueryRes;

public interface BaiChuanService {
    BaiChuanAddAndUpdateRes dealAdd(BaiChuanAddAndUpdateReq req);

    BaiChuanQueryRes dealQuery(BaiChuanQueryReq req);

    BaiChuanDeleteRes dealDelete(int id);

    BaiChuanAddAndUpdateRes dealUpdate(BaiChuanAddAndUpdateReq req,int id);

    BaiChuanChatRes dealChat(BaiChuanChatReq req);

    String dealMsg(String msg);
}
