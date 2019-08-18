package com.zyzx.redbag.rabbitmq;


import com.zyzx.redbag.entry.RedPacket;
import com.zyzx.redbag.entry.UserClick;
import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.service.RankService;
import com.zyzx.redbag.service.RedBagService;
import com.zyzx.redbag.util.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangyunxiong on 2018/5/29.
 */
@Service
public class MQReceiver {

    @Autowired
    RedisService redisService;

    @Autowired
    RedBagService redBagService;
    @Autowired
    RankService rankService;

    @RabbitListener(queues = MQConfig.REDBAG_TOPIC)
    public void receiveRedBag(String message) {
        redBagService.insertRedBag(message);
    }
     @RabbitListener(queues = MQConfig.RANK_TOPIC)
        public void receiveRankList(String message) {
         UserClick userClick = RedisService.stringToBean(message,UserClick.class);
            rankService.InsertRanking(userClick);
        }

    @RabbitListener(queues = MQConfig.PRECLICK_TOPIC)
    public void receivePreClick(String message) {
        System.out.println(" topic  queue1 message:" + message);
        UserClick userClick = RedisService.stringToBean(message,UserClick.class);

    }

}
