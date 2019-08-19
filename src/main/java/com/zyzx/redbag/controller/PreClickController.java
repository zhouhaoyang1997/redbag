package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.Result;
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
    public  Result<String> preClick(UserClick userClick){

    Result result=preClickService.preClick(userClick);
        return result;
    }
}
