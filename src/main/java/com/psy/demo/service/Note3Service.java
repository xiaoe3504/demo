package com.psy.demo.service;


import com.psy.demo.dto.Note3DTO;
import com.psy.demo.dto.UserInfoDTO;

import java.util.List;

public interface Note3Service {

    int insert(Note3DTO note3DTO);

    List<Note3DTO> selectByOpenId(String openId);

}
