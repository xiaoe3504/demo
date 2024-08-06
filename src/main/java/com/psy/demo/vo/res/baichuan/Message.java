package com.psy.demo.vo.res.baichuan;

import com.psy.demo.vo.res.RoleContent;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String role;
    private String content;
}



