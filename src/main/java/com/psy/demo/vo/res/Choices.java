package com.psy.demo.vo.res;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Choices {
    List<Text> text;
}