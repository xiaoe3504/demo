package com.psy.demo.vo.res;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.enums.PayCategoryEnum;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.utils.StringUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Builder
public class OrderAmountVO {
    /**
     * 订单量
     */
    private int orderCnt;
    /**
     * 收入(元)
     */
    private int amount;
    /**
     * 收入(元) string 格式的
     */
    private String amountStr;

}


