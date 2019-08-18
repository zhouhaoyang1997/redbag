package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.User;
import com.zyzx.redbag.service.UserService;
import com.zyzx.redbag.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author zhy
 * @create 2019-08-18 12:50
 **/
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public User login(String tel, HttpSession session){
        if (ValidatorUtil.isMobile(tel)){
            userService.insertUser(tel);
            session.setAttribute(Const.USER,User);
//            后续判断是否已经领过将
//            code
//
            return User;
        }
        return Const.FALSE;
    }
}
