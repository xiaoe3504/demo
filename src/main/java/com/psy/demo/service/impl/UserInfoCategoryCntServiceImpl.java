package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.dto.UserInfoCategoryCntDTO;
import com.psy.demo.enums.PayCategoryEnum;
import com.psy.demo.ext.SimpleHttpClient;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.UserInfoCategoryCntMapper;
import com.psy.demo.service.BaiChuanService;
import com.psy.demo.service.UserInfoCategoryCntService;
import com.psy.demo.utils.CommonUtils;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.vo.req.baichuan.BaiChuanAddAndUpdateReq;
import com.psy.demo.vo.req.baichuan.BaiChuanChatReq;
import com.psy.demo.vo.req.baichuan.BaiChuanQueryReq;
import com.psy.demo.vo.req.baichuan.CharacterProfile;
import com.psy.demo.vo.res.baichuan.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.psy.demo.utils.MyConstantString.*;


@Service
@Slf4j
public class UserInfoCategoryCntServiceImpl implements UserInfoCategoryCntService {


    @Autowired
    UserInfoCategoryCntMapper userInfoCategoryCntMapper;


    @Override
    public int insertOrUpdateCnt(UserInfoCategoryCntDTO dto) {
        dealValidate(dto);
        return userInfoCategoryCntMapper.insertOrUpdateCnt(dto);
    }


    @Override
    public int selectCntByUK(UserInfoCategoryCntDTO dto) {
        dealValidate(dto);
        return userInfoCategoryCntMapper.selectCntByUK(dto);
    }


    private void dealValidate(UserInfoCategoryCntDTO dto) {
        if (StringUtils.isEmpty(dto.getOpenId())) {
            throw new BaseException("openId不能为空");
        }
        if (StringUtils.isEmpty(dto.getCategory())) {
            throw new BaseException("category不能为空");
        }
        if (dto.getCategoryId() <= 0) {
            throw new BaseException("categoryId不能为空或负数");
        }
        if (!PayCategoryEnum.isCategoryType(dto.getCategory())) {
            throw new BaseException("Category wrong enum err");
        }
    }
}
