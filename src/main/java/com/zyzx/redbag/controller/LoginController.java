package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.util.ValidatorUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author zhy
 * @create 2019-08-18 12:50
 **/
@RestController
public class LoginController {
    public String login(String tel, HttpSession session){
        if (ValidatorUtil.isMobile(tel)){
            session.setAttribute(Const.TEL,tel);
            return Const.SUCCESS;
        }
        return Const.FALSE;
    }
}
