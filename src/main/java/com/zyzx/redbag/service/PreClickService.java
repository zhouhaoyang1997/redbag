package com.zyzx.redbag.service;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.rabbitmq.MQConfig;
import com.zyzx.redbag.rabbitmq.MQSender;
import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PreClickService {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    RedisService redisService;
    @Autowired
    MQSender sender;
    public String preClick(UserClick userClick){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        String data=df.format(new Date());
        userClick.setCompleteTime(data);
        Jedis jedis=jedisPool.getResource();

        jedis.rpush(String.valueOf(userClick.getUserId()), JsonUtil.obj2String(userClick));
        savePreClick(userClick);
        //此处可在插入后判断是否到达指定数目
        return Const.SUCCESS;

    }

    public void savePreClick(UserClick userClick){
        sender.send(userClick, MQConfig.PRECLICK_TOPIC);
    }
}
