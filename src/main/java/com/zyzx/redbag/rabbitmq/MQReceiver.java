package com.zyzx.redbag.rabbitmq;


import com.zyzx.redbag.entry.RedPacket;
import com.zyzx.redbag.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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




    @RabbitListener(queues = MQConfig.REDBAG_TOPIC)
    public void receiveRedBag(String message) {
        System.out.println(" topic  queue1 message:" + message);
        RedPacket redPacket = RedisService.stringToBean(message, RedPacket.class);
    }


}
