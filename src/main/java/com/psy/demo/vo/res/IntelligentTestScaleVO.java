package com.psy.demo.vo.res;


import com.psy.demo.dto.IntelligentTestScaleDTO;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntelligentTestScaleVO {
    private long id;
    private String name;
    private String title;
    private String textCnt;
    private boolean isFree;


    public static IntelligentTestScaleVO genVOByDTO(IntelligentTestScaleDTO dto) {
        IntelligentTestScaleVO vo = new IntelligentTestScaleVO();
        vo.setId(dto.getId());
        vo.setName(dto.getName());
        vo.setTitle(dto.getTitle());
        vo.setTextCnt(String.valueOf(dto.getCnt()));
        String isFreeStr = dto.getIsFree().trim();
        vo.setFree(StringUtils.isNotEmpty(isFreeStr) && isFreeStr.equals("免费"));
        return vo;
    }

}


