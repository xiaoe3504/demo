package com.psy.demo.vo.res.baichuan;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
    private int search_count;
}
