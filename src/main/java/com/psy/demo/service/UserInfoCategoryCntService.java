package com.psy.demo.service;


import com.psy.demo.dto.UserInfoCategoryCntDTO;
import com.psy.demo.vo.req.baichuan.BaiChuanAddAndUpdateReq;
import com.psy.demo.vo.req.baichuan.BaiChuanChatReq;
import com.psy.demo.vo.req.baichuan.BaiChuanQueryReq;
import com.psy.demo.vo.res.baichuan.BaiChuanAddAndUpdateRes;
import com.psy.demo.vo.res.baichuan.BaiChuanChatRes;
import com.psy.demo.vo.res.baichuan.BaiChuanDeleteRes;
import com.psy.demo.vo.res.baichuan.BaiChuanQueryRes;

public interface UserInfoCategoryCntService {
    int insertOrUpdateCnt(UserInfoCategoryCntDTO dto);

    int selectCntByUK(UserInfoCategoryCntDTO dto);


}
