package com.psy.demo.vo.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.psy.demo.dto.IntelligentTestScaleDTO;
import com.psy.demo.utils.CommonUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        String textCnt = getTestCnt(dto.getCnt());
        vo.setTextCnt(textCnt);
        String isFreeStr = dto.getIsFree().trim();
        boolean isFree = StringUtils.isNotEmpty(isFreeStr) && isFreeStr.equals("免费");
        vo.setFree(isFree);
        double price = CommonUtils.getPrice(isFreeStr, isFree);
        vo.setPrice(price);
        vo.setPayed(dto.isPayed());
        return vo;
    }

    @NotNull
    private static String getTestCnt(Long cnt) {
        if (cnt > 10000) {
            double a = cnt / 10000.0;
            BigDecimal bd = new BigDecimal(a).setScale(1, RoundingMode.HALF_UP); // 结果是 3.14
            return bd.toString() + "w人已测";
        } else {
            return cnt + "人已测";
        }
    }

}


