package com.zyzx.redbag.service;

import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.entry.Result;
import com.zyzx.redbag.entry.User;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.mapper.RankMapper;
import com.zyzx.redbag.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;


@Service
public class RankService {
    @Autowired
    JedisPool jedisPool;
   @Autowired
    RankMapper rankMapper;
    public Result<List> getRank(){
        Jedis jedis =jedisPool.getResource();
        List<String> strList=jedis.lrange(Const.RANKLIST,0,Const.ALLREDBAGNUM);
        List<Ranking> rankingList1=new ArrayList<Ranking>();

        for (String rstr:strList) {
            rankingList1.add(JsonUtil.string2Obj(rstr, Ranking.class));
        }


        return new Result<List>("0",Const.SUCCESS,rankingList1);

    }
   public void InsertRanking(UserClick userClick){
        rankMapper.insertRanking(userClick);
    }
}
