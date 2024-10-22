package com.psy.demo.service;


import com.psy.demo.vo.req.CommonTypeReq;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResFinalVO;


public interface IntelligentTestScaleService {

    IntelligentTestScaleTypeResFinalVO select(String openId);

    IntelligentTestScaleTypeResFinalVO selectType(CommonTypeReq req);
}
