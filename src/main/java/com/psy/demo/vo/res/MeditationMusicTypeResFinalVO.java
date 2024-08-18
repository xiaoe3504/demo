package com.psy.demo.vo.res;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeditationMusicTypeResFinalVO {
    @JsonProperty("isMember")
    private boolean isMember;
    private List<MeditationMusicTypeResVO> list;
}
