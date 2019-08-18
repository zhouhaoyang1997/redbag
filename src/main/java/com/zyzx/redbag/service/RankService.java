package com.zyzx.redbag.service;

import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankService {
    @Autowired
    JedisPool jedisPool;
    public List<Ranking> getRank(){
        Jedis jedis =jedisPool.getResource();
        List<String> strList=jedis.lrange("rankList",0,30);
        List<Ranking> rankingList1=new ArrayList<Ranking>();


        for (String rstr:strList) {
            rankingList1.add(JsonUtil.string2Obj(rstr,Ranking.class));
        }

        return  rankingList1;

    }
}
