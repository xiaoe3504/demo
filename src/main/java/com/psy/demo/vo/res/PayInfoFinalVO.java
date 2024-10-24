package com.psy.demo.vo.res;


import com.psy.demo.dto.PayInfoDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Builder
public class PayInfoFinalVO {
    /**
     * 类型
     */
    private String category;
    private String description;
    private String amount;
    private String tradeNo;
    private String createTime;

}


