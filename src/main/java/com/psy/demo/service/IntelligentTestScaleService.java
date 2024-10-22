package com.psy.demo.service;


import com.psy.demo.vo.req.CommonTypeReqVO;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResFinalVO;


public interface IntelligentTestScaleService {

    IntelligentTestScaleTypeResFinalVO select(String openId);

    IntelligentTestScaleTypeResFinalVO selectType(CommonTypeReqVO req);
}
