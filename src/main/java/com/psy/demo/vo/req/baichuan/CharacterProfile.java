package com.psy.demo.vo.req.baichuan;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterProfile {
    private String character_name;
    private String character_info;
    private String user_name;
    private String user_info;
    private int character_id;
}
