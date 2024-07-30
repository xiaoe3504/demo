package com.psy.demo.vo.res;

import com.psy.demo.dto.Note3DTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
    * 三栏笔记
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Note3VO {

    static Map<String, String> mapTypeLabel;

    static {
        mapTypeLabel = new HashMap<>();
        mapTypeLabel.put( "1","absolutization");
        mapTypeLabel.put( "2","generalization");
        mapTypeLabel.put( "3","catastrophic");
    }
    /**
    * id
    */
    private Long id;

    /**
    * 用户open_id唯一的
    */
    private String openId;

    /**
    * 自动化负性观念
    */
    private String mood;

    /**
    * 不合理认知分析类型
    */
    private String analysisType;

    /**
    * 不合理认知分析类型名
    */
    private String analysis;

    /**
     * 不合理认知分析label
     */
    private String label;


    /**
    * 不合理认知分析详情
    */
    private String analysisDetail;

    /**
    * 我的理性回应
    */
    private String defend;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    public static Note3VO genVOByDTO(Note3DTO dto){
        Note3VO vo=new Note3VO();
        vo.setId(dto.getId());
        vo.setOpenId(dto.getOpenId());
        vo.setMood(dto.getMood());
        vo.setAnalysisType(dto.getAnalysisType());
        vo.setAnalysis(dto.getAnalysis());
        vo.setAnalysisDetail(dto.getAnalysisDetail());
        vo.setDefend(dto.getDefend());
        vo.setCreateTime(dto.getCreateTime());
        vo.setUpdateTime(dto.getUpdateTime());
        vo.setLabel(mapTypeLabel.getOrDefault(dto.getAnalysisType(),"absolutization"));
        return vo;
    }


}