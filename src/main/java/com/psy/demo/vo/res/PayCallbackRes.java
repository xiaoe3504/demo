package com.psy.demo.vo.res;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayCallbackRes {
    private String id;
    private Date create_time;
    private String resource_type;
    private String event_type;
    private String summary;
    private PayCallbackResourceRes resource;

}
