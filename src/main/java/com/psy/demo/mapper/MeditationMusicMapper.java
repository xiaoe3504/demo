package com.psy.demo.mapper;

import com.psy.demo.dto.MeditationMusicDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeditationMusicMapper {


    List<MeditationMusicDTO> select();


}