package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.entry.Result;
import com.zyzx.redbag.entry.User;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.rabbitmq.MQConfig;
import com.zyzx.redbag.rabbitmq.MQSender;
import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.service.RankService;
import com.zyzx.redbag.service.UserService;
import com.zyzx.redbag.util.UserUtil;
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
    public Result clickRedBag(UserClick userClick, HttpSession session){
        if(!UserUtil.checkUser(session)){
            return new Result("-1","用户未登录");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        String data=df.format(new Date());
        userClick.setCompleteTime(data);
        User user= (User) session.getAttribute(Const.USER);
        long len = redisService.getLen(String.valueOf(userClick.getUserId()));

        if(len == Const.PRECLICK){
            userService.updateUserIsPartake(userClick.getUserId());
            //实例话rank
            Ranking ranking=new Ranking();
            ranking.setUserId(userClick.getUserId());
            ranking.setUserClickId(userClick.getUserClickId());
            ranking.setCompleteTime(userClick.getCompleteTime());
            ranking.setUserTel(user.getUserTel());
            //获取插入时index
              long index = redisService.orderAdd(Const.RANKLIST,ranking);
              //推送
              sender.send(userClick,MQConfig.PRECLICK_TOPIC);
              //
              if(index<=Const.ALLREDBAGNUM){
                  if(index<=Const.FIRSTREWARD){
                      ranking.setWinningLevel(1);
                  }
                  else if(index<=(Const.SECONDREWARD+Const.FIRSTREWARD)){
                      ranking.setWinningLevel(2);
                  }
                  else if(index<=(Const.SECONDREWARD+Const.FIRSTREWARD+Const.THIRDREWARD)){
                      ranking.setWinningLevel(3);
                  }else{
                      ranking.setWinningLevel(0);
                  }
                  redisService.orderAdd(Const.REWARD,ranking);
                  rankService.InsertRanking(ranking);
                  return new Result("0",Const.SUCCESS);
              }
            return new Result("-1","没有中奖");
        }else {
            return new Result("-1","违法操作");
        }

    }
}
