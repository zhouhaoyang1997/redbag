package com.zyzx.redbag.controller;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.RedPacket;
import com.zyzx.redbag.rabbitmq.MQSender;
import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    MQSender sender;
    @GetMapping("/getRedBag")
    public String getRedBag(){
        RedPacket redPacket = new RedPacket();
        redPacket.setRedPacketId(UUID.randomUUID().toString());
        String uuid =redPacket.getRedPacketId();
        redisService.set(Const.REDBAGKEY,redPacket);
        //放进MQ
        sender.send(redPacket);
        // code
        //
        return uuid;
    }
}
