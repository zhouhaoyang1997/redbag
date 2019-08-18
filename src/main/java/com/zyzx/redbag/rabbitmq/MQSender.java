package com.zyzx.redbag.rabbitmq;

import com.zyzx.redbag.redis.RedisService;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object message,String topic){
        String msg = RedisService.beanToString(message);
        System.out.println(topic);
        amqpTemplate.convertAndSend(topic, msg);
    }


}
