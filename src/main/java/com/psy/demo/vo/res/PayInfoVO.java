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
public class PayInfoVO {
    /**
     * 大类，TYPE_TEST,TYPE_MEDITATION两种
     */
    private String category;

    private List<PayInfoDTO> dtos;


}


