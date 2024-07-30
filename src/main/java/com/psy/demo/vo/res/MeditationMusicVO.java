package com.psy.demo.vo.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.psy.demo.dto.MeditationMusicDTO;
import com.psy.demo.utils.CommonUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class MeditationMusicVO {
    private long id;
    private String name;
    private String mp3Name;
    private String title;
    private String duration;
    @JsonProperty("isFree")
    private boolean isFree;
    private double price;

    public static MeditationMusicVO genVOByDTO(MeditationMusicDTO dto) {
        MeditationMusicVO vo = new MeditationMusicVO();
        vo.setId(dto.getId());
        vo.setName(dto.getName());
        vo.setMp3Name(dto.getMp3Name());
        vo.setTitle(dto.getTitle());
        vo.setDuration("基础"+dto.getDuration()+"分钟");
        String isFreeStr = dto.getIsFree().trim();
        boolean isFree = StringUtils.isNotEmpty(isFreeStr) && isFreeStr.equals("免费");
        vo.setFree(isFree);
        double price = CommonUtils.getPrice(isFreeStr, isFree);
        vo.setPrice(price);
        return vo;
    }



}


