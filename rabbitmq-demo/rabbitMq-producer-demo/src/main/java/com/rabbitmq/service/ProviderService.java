package com.rabbitmq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */

@Component
@RequiredArgsConstructor
public class ProviderService {

    private final RabbitTemplate rabbitTemplate;


    public void providerMethod(){
        String  queueName = "simple.name";
        String message = "hello ,spring amqp";

        rabbitTemplate.convertAndSend(queueName,message);
    }

}
