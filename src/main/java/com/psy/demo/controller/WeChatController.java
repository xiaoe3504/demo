package com.psy.demo.controller;

import com.psy.demo.service.HttpClientService;
import com.psy.demo.service.PayInfoService;
import com.psy.demo.service.WeChatService;
import com.psy.demo.utils.GetTokenUtils;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.req.SignReq;
import com.psy.demo.vo.res.PayRes;
import com.psy.demo.vo.res.SignRes;
import com.psy.demo.vo.res.WeChatCerData;
import com.psy.demo.vo.res.WeChatCerRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    WeChatService weChatService;
    @Autowired
    HttpClientService httpClientService;

    @GetMapping(path = "/dealCer")
    public WeChatCerRes dealCer() {
        return weChatService.dealCer();
    }

    @GetMapping(path = "/getCerText")
    public WeChatCerRes getCerText() {
        return httpClientService.getCerText();
    }

}
