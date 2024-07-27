package com.psy.demo.vo.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.psy.demo.dto.IntelligentTestScaleDTO;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IntelligentTestScaleVO {
    private long id;
    private String name;
    private String title;
    private String textCnt;
    private String sid;
    private String hash;
    @JsonProperty("isFree")
    private boolean isFree;


    public static IntelligentTestScaleVO genVOByDTO(IntelligentTestScaleDTO dto) {
        IntelligentTestScaleVO vo = new IntelligentTestScaleVO();
        vo.setId(dto.getId());
        vo.setName(dto.getName());
        vo.setTitle(dto.getTitle());
        vo.setSid(dto.getSid());
        vo.setHash(dto.getHash());
        vo.setTextCnt(dto.getCnt() +"人参与");
        String isFreeStr = dto.getIsFree().trim();
        vo.setFree(StringUtils.isNotEmpty(isFreeStr) && isFreeStr.equals("免费"));
        return vo;
    }

}


