package com.psy.demo.service;


import com.psy.demo.vo.res.IntelligentTestScaleTypeResVO;

import java.util.List;

public interface IntelligentTestScaleService {

    List<IntelligentTestScaleTypeResVO> select(String openId);

}
