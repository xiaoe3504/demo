package com.psy.demo.service;


import com.psy.demo.dto.Note3DTO;
import com.psy.demo.vo.res.Note3VO;

import java.util.List;

public interface Note3Service {

    int insert(Note3DTO note3DTO);

    List<Note3VO> selectByOpenId(String openId);

}
