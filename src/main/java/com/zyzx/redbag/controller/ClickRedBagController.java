package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.rabbitmq.MQConfig;
import com.zyzx.redbag.rabbitmq.MQSender;
import com.zyzx.redbag.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    @PostMapping
    public  String clickRedBag(UserClick userClick, HttpSession session){
        long len = redisService.getLen(String.valueOf(userClick.getUserId()));
        if(len == Const.PRECLICK){
              long index = redisService.orderAdd(Const.RANKLIST,userClick);
              if(index<=Const.ALLREDBAGNUM){
                  sender.send(userClick, MQConfig.RANK_TOPIC);
                  return Const.ZHONGJIANG;
              }
              
              return Const.FALSE;
//            if(redisService.getLen(Const.RANKLIST)==Const.CLICKREDBAGNUM&&redisService.getBeanByIndex(Const.RANKLIST,Const.ALLREDBAGNUM,UserClick.class).getUserId()==userClick.getUserId()){
//            }

        }else {
            return Const.ERROR;
        }
    }
}
