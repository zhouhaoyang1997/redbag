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
        List<String> strList=jedis.lrange(Const.REWARD,0,Const.ALLREDBAGNUM);
        List<Ranking> rankingList1=new ArrayList<Ranking>();
        if (!strList.isEmpty()){
            for (String rstr:strList) {
                rankingList1.add(JsonUtil.string2Obj(rstr, Ranking.class));
            }


            return new Result<List>("0",Const.SUCCESS,rankingList1);
        }else {
            //在redis缓存没有的情况下访问数据库
            rankingList1=rankMapper.getRank();
            if (!rankingList1.isEmpty()){
                return new Result<List>("0",Const.SUCCESS,rankingList1);
            }else{
                //数据库没有，报错
                return new Result<List>("-1",Const.FALSE);
            }

        }



    }
   public void InsertRanking(Ranking ranking){
        rankMapper.insertRanking(ranking);
    }
}
