package com.psy.demo.vo.req;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackReq {
    private Long id;
    private String feedback;
}
