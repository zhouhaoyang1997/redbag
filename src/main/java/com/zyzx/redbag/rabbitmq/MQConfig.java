package com.zyzx.redbag.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {


    public static final String REDBAG_TOPIC = "redbagtopic";
    public static final String PRECLICK_TOPIC = "preClick";
//
//    @Bean
//    public Queue preClickTopic() {
//        return new Queue(PRECLICK_TOPIC, true);
//    }
//
//
//    @Bean
//    public Queue redBagTopic() {
//        return new Queue(REDBAG_TOPIC, true);
//    }



}
