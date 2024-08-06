package com.psy.demo.vo.res.baichuan;

import com.psy.demo.vo.req.baichuan.DialogSample;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiChuanQueryListRes {
    private int id;
    private String object;
    private String name;
    private String opener;
    private double temperature;
    private List<String> personality_list;
    private String model;
    private String biography;
    private String relationships;
    private String profile_image_url;
    private String basic_info;
    private double top_p;
    private int max_tokens;
    private String user_nickname;
    private String user_gender;
    private String personality_complement;
    private String reply_restrict;
    private List<String> knowledge_ids;
    private String user_info;
    private List<DialogSample> dialog_sample;
}
