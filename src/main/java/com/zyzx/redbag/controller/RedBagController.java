package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.RedPacket;
import com.zyzx.redbag.entry.Result;
import com.zyzx.redbag.rabbitmq.MQConfig;
import com.zyzx.redbag.rabbitmq.MQSender;
import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.service.RedBagService;
import com.zyzx.redbag.util.JsonUtil;
import com.zyzx.redbag.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;


/**
 * @author zhy
 * @create 2019-08-18 15:12
 **/
@RestController
public class RedBagController {
    @Autowired
    RedisService redisService;
    @Autowired
    RedBagService redBagService;
    @Autowired
    MQSender sender;
    @GetMapping("/getRedBag")
    public Result getRedBag(HttpSession session){
        if(!UserUtil.checkUser(session)){
            return new Result("-1","用户未登录");
        }
        RedPacket redPacket = new RedPacket();
        redPacket.setRedPacketId(UUID.randomUUID().toString());
        String uuid =redPacket.getRedPacketId();
        redisService.set(Const.REDBAGKEY,redPacket);
        redBagService.insertRedBag(uuid);
        return new Result("0",Const.SUCCESS,uuid);
    }

}
