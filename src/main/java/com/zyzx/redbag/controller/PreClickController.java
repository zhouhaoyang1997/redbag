package com.zyzx.redbag.controller;

import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.service.PreClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreClickController {
    @Autowired
    private PreClickService preClickService;

    @PostMapping ("/preClick")
    public  String preClick(UserClick userClick){

    String result=preClickService.preClick(userClick);
        return result;
    }
}
