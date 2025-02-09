package com.psy.demo.dto;

import java.time.LocalDateTime;

import com.psy.demo.global.BaseException;
import com.psy.demo.vo.res.OrganizationVO;
import com.psy.demo.vo.res.PsychologistVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 机构表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 机构名称
    */
    private String name;

    /**
    * 敲击数
    */
    private Integer memberCnt;

    /**
    * 失效时间
    */
    private LocalDateTime expiredTime;

    /**
    * 支付类别,0成长中心，咨询师，音频都需要付费，1成长中心，咨询师需要付费，2成长中心需付费
    */
    private Integer payType;

    /**
    * 姓名检查类别,0无需检查，1需要检查
    */
    private Integer checkNameType;

    /**
    * 是否删除
    */
    private Integer isDel;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    public static OrganizationVO genVO(OrganizationDTO dto){
        if (dto == null) {
            throw new BaseException("genPsychologistVO error dto is null...");
        }
        OrganizationVO res=new OrganizationVO();
        res.setId(String.valueOf(dto.getId()));
        res.setName(dto.getName());
        return res;
    }
}