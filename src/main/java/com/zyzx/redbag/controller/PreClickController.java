package com.zyzx.redbag.controller;

import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.service.PreClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PreClickController {
    @Autowired
    private PreClickService preClickService;

    @RequestMapping("/preClick")
    @ResponseBody
    public  String preClick(UserClick userClick){
    String result=preClickService.preClick(userClick);
        return result;
    }
}
