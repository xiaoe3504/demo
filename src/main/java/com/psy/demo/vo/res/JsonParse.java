package com.psy.demo.vo.res;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonParse {
    Header header;
    Payload payload;
}