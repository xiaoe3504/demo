package com.psy.demo.mapper;

import com.psy.demo.dto.MeditationMusicDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeditationMusicMapper {


    List<MeditationMusicDTO> select(@Param("type") String type);


}