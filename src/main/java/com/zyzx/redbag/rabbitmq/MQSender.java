package com.zyzx.redbag.rabbitmq;

import com.zyzx.redbag.redis.RedisService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.oracle.tools.packager.Log.info;

/**
 * Created by jiangyunxiong on 2018/5/29.
 */
@Slf4j
@Service
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object message){
        String msg = RedisService.beanToString(message);
        amqpTemplate.convertAndSend(MQConfig.REDBAG_TOPIC, msg);
    }


}
