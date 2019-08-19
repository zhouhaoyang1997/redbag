package com.zyzx.redbag.controller;

import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.entry.Result;
import com.zyzx.redbag.service.RankService;
import com.zyzx.redbag.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RankController {

    @Autowired
    private RankService rankService;
    @RequestMapping("/getRank")
    @ResponseBody
    public  Result getRank(HttpSession session){
        if(!UserUtil.checkUser(session)){
            return new Result("-1","用户未登录");
        }
    Result result =rankService.getRank();
        return result;
    }
}
