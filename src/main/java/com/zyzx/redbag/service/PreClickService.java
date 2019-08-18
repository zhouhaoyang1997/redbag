package com.zyzx.redbag.service;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class PreClickService {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    RedisService redisService;
    public String preClick(UserClick userClick){
       Jedis jedis=jedisPool.getResource();
       if (jedis.exists(userClick.getUserId()+"")){
         jedis.rpush(userClick.getUserId()+"", JsonUtil.obj2String(userClick));
         //此处可在插入后判断是否到达指定数目
         return Const.SUCCESS;
       }else{

       }

        return"";
    }
}
