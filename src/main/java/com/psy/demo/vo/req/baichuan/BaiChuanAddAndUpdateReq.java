package com.psy.demo.vo.req.baichuan;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiChuanAddAndUpdateReq {
    private String name;
    private String basic_info;
    private String opener;
    private List<String> personality_list;
    private String personality_complement;
    private String biography;
    private String relationships;
    private String profile_image_url;
    private String user_nickname;
    private String user_gender;
    private String user_info;
    private List<DialogSample> dialog_sample;
    private String model;
    private String reply_restrict;
    private List<String> knowledge_ids;
    private double temperature;
    private double top_p;
    private int max_tokens;
}
