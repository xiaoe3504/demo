package com.psy.demo.mapper;

import com.psy.demo.dto.MoodMapDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoodMapMapper {
    List<MoodMapDTO> selectByOpenId(String openId);

    int insertOrUpdate(MoodMapDTO dto);

    MoodMapDTO getMoodToday(MoodMapDTO dto);
}