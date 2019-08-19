package com.zyzx.redbag.service;


import com.zyzx.redbag.entry.Result;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.mapper.PreClickMapper;

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
    PreClickMapper mapper;
    public Result preClick(UserClick userClick){

        if (userClick==null){
            return  new Result("-1","param is null");
        }
//        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
//        String data=df.format(new Date());
//        userClick.setCompleteTime(data);
        Jedis jedis=jedisPool.getResource();

        jedis.rpush(String.valueOf(userClick.getUserId()), JsonUtil.obj2String(userClick));
        jedis.close();
        savePreClick(userClick);
        //此处可在插入后判断是否到达指定数目

        return  new Result("0","success");

    }

    public void savePreClick(UserClick userClick){

       /* sender.send(userClick, MQConfig.PRECLICK_TOPIC);*/
        mapper.insertPreclick(userClick);
    }
}
