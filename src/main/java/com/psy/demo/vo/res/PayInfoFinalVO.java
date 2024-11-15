package com.psy.demo.vo.res;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.enums.PayCategoryEnum;
import com.psy.demo.utils.DateUtils;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.utils.StringUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
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
public class PayInfoFinalVO {
    /**
     * 类型
     */
    private Long id;
    private String category;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String feedback;

    private boolean canWithdrawal;

    private List<PayInfoFinalInnerVO>list;



    public static PayInfoFinalVO genVO(PayInfoDTO dto) {
        String description = dto.getDescription();
        String amount = StringUtil.getPriceString(dto.getAmount());
        String tradeNo = dto.getTradeNo();
        String createTime = dto.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));

        List<PayInfoFinalInnerVO> list=new ArrayList<>();

        dealInnerVO(description, list, MyConstantString.ORDER_DESCRIPTION);
        dealInnerVO(amount, list, MyConstantString.ORDER_PRICE);
        dealInnerVO(tradeNo, list, MyConstantString.ORDER_NO);
        dealInnerVO(createTime, list, MyConstantString.ORDER_TIME);

        //判断提现时间
        LocalDateTime orderTime = dto.getCreateTime();
        boolean canWithdrawal=DateUtils.validWithdrawalTime(orderTime);

        return PayInfoFinalVO.builder()
                .id(dto.getId())
                .category(PayCategoryEnum.getDescByName(dto.getCategory()))
                .feedback(dto.getFeedback())
                .canWithdrawal(canWithdrawal)
                .list(list)
                .build();
    }

    private static void dealInnerVO(String attr, List<PayInfoFinalInnerVO> list, String constantStr) {
        if (StringUtils.isNotEmpty(attr)) {
            PayInfoFinalInnerVO innerVO = PayInfoFinalInnerVO.builder()
                    .name(constantStr)
                    .detail(attr)
                    .build();
            list.add(innerVO);
        }
    }

}


