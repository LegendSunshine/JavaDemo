package com.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */

@EnableRabbit
@SpringBootApplication
public class ComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComsumerApplication.class,args);
    }
}
