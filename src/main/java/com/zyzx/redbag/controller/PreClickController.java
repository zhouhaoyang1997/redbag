package com.zyzx.redbag.controller;

import com.zyzx.redbag.entry.UserClick;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PreClickController {
    @RequestMapping("/preClick")
    public  String preClick(UserClick userClick){


        return"";
    }
}
