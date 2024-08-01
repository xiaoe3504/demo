package com.psy.demo.vo.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.psy.demo.dto.IntelligentTestScaleDTO;
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
public class IntelligentTestScaleVO {
    private long id;
    private String name;
    private String title;
    private String textCnt;
    private String sid;
    private String hash;
    @JsonProperty("isFree")
    private boolean isFree;
    private double price;
    @JsonProperty("isPayed")
    private boolean isPayed;

    public static IntelligentTestScaleVO genVOByDTO(IntelligentTestScaleDTO dto) {
        IntelligentTestScaleVO vo = new IntelligentTestScaleVO();
        vo.setId(dto.getId());
        vo.setName(dto.getName());
        vo.setTitle(dto.getTitle());
        vo.setSid(dto.getSid());
        vo.setHash(dto.getHash());
        vo.setTextCnt(dto.getCnt() + "人参与");
        String isFreeStr = dto.getIsFree().trim();
        boolean isFree = StringUtils.isNotEmpty(isFreeStr) && isFreeStr.equals("免费");
        vo.setFree(isFree);
        double price = CommonUtils.getPrice(isFreeStr, isFree);
        vo.setPrice(price);
        vo.setPayed(dto.isPayed());
        return vo;
    }

}


