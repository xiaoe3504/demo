package com.psy.demo.vo.res;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPhoneNumberPhoneInfo {
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private GetPhoneNumberWatermark watermark;

}
