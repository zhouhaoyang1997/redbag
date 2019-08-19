package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.User;
import com.zyzx.redbag.service.UserService;
import com.zyzx.redbag.util.DateTimeUtil;
import com.zyzx.redbag.util.JsonUtil;
import com.zyzx.redbag.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author zhy
 * @create 2019-08-18 12:50
 **/
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(String tel, HttpSession session){
        Date begintime = DateTimeUtil.strToDate(Const.BEGINTIME);
        Date endtime = DateTimeUtil.strToDate(Const.ENDTIME);
        Date nowtime = new Date();
        if(nowtime.before(begintime)){
            return "活动未开始";
        }else if(nowtime.after(endtime)){
            return "活动未开始";
        }
        if (ValidatorUtil.isMobile(tel)){
            User user = userService.checkIsPartake(tel);
            if (user!=null){
                session.setAttribute(Const.USER,user);
                return JsonUtil.obj2String(user);
            }else {
                userService.insertUser(tel);
                User user1 = userService.checkUser(tel);
                session.setAttribute(Const.USER,user1);
                return JsonUtil.obj2String(user1);
            }
        }else {
            return "手机号格式错误";
        }
    }
}
