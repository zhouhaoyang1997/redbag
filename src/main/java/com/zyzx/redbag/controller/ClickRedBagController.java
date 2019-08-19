package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.entry.User;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.rabbitmq.MQConfig;
import com.zyzx.redbag.rabbitmq.MQSender;
import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.service.RankService;
import com.zyzx.redbag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhy
 * @create 2019-08-18 20:39
 **/
@RestController
public class ClickRedBagController {
    @Autowired
    RedisService redisService;
    @Autowired
    MQSender sender;
    @Autowired
    UserService userService;
    @Autowired
    RankService rankService;
    @RequestMapping("/clickRedBag")
    public  String clickRedBag(UserClick userClick,HttpSession session){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        String data=df.format(new Date());
        userClick.setCompleteTime(data);
        User user= (User) session.getAttribute(Const.USER);
        Ranking ranking=new Ranking();
        ranking.setUserId(userClick.getUserId());
        ranking.setUserClickId(userClick.getUserClickId());
        ranking.setCompleteTime(userClick.getCompleteTime());
        ranking.setUserTel(user.getUserTel());
        long len = redisService.getLen(String.valueOf(userClick.getUserId()));
        if(len == Const.PRECLICK){
              long index = redisService.orderAdd(Const.RANKLIST,ranking);
              sender.send(userClick,MQConfig.PRECLICK_TOPIC);
              if(index<=Const.ALLREDBAGNUM){
                  userService.updateUserIsPartake(userClick.getUserId());
                  rankService.InsertRanking(userClick);
                  sender.send(userClick, MQConfig.RANK_TOPIC);
                  return Const.ZHONGJIANG;
              }
              return Const.BUZHONGJIANG;
        }else {
            return Const.ERROR;
        }
    }
}
