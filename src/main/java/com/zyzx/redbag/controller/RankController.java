package com.zyzx.redbag.controller;

import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RankController {
    @Autowired
    JedisPool jedisPool;


    public  String getRank(){



        return  "";
    }
}
