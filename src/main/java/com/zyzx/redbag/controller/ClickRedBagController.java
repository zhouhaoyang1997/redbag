package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.entry.Result;
import com.zyzx.redbag.entry.User;
import com.zyzx.redbag.entry.UserClick;

import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.service.PreClickService;
import com.zyzx.redbag.service.RankService;
import com.zyzx.redbag.service.UserService;
import com.zyzx.redbag.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserService userService;
    @Autowired
    RankService rankService;
    @Autowired
    private PreClickService preClickService;
    @RequestMapping("/clickRedBag")
    public Result clickRedBag(UserClick userClick, HttpSession session){
        if(Const.ISOVER){
            return new Result("400","gameover");
        }
        if(!UserUtil.checkUser(session)){
            return new Result("-1","用户未登录");
        }
        if(userClick==null||redisService.exists(userClick.getRedpacketId())){
            return new Result("-1","非法操作");
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
              ranking.setRankingId((int) index);
              //推送
              preClickService.savePreClick(userClick);
              preClickService.preClick(userClick);
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
                  return new Result("0",Const.SUCCESS,ranking);
              }
              //更改全局变量
              Const.ISOVER = true;
            return new Result("-1","没有中奖");
        }else if(len<Const.PRECLICK){
            return preClickService.preClick(userClick);
        }else {
            return new Result("-1","违法操作");
        }

    }

    //测试接口
    @RequestMapping("/test/clickRedBag")
    public Result clickRedBagTest(UserClick userClick){
        if(Const.ISOVER_TEST){
            return new Result("400","gameover");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        String data=df.format(new Date());
        userClick.setCompleteTime(data);
//        User user= (User) session.getAttribute(Const.USER);
        long len = redisService.getLen(String.valueOf(userClick.getUserId()));
        if(len == Const.PRECLICK){
            userService.updateUserIsPartake(userClick.getUserId());
            //实例话rank
            Ranking ranking=new Ranking();
            ranking.setUserId(userClick.getUserId());
            ranking.setUserClickId(userClick.getUserClickId());
            ranking.setCompleteTime(userClick.getCompleteTime());
//            ranking.setUserTel(user.getUserTel());
            //获取插入时index
            long index = redisService.orderAdd(Const.RANKLIST,ranking);
            ranking.setRankingId((int) index);
            //推送
            preClickService.savePreClick(userClick);
            preClickService.preClick(userClick);
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
                return new Result("0",Const.SUCCESS,ranking);
            }
            //更改全局变量
            Const.ISOVER_TEST = true;
            return new Result("-1","没有中奖");
        }else if(len<Const.PRECLICK){
            return preClickService.preClick(userClick);
        }else {
            return new Result("-1","违法操作");
        }

    }
}
