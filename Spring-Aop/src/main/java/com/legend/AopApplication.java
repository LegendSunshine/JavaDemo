package com.legend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @className AopApplication
 * @description:
 * @author legend
 * @date 2024/12/31 23:05
 * @version 1.0
 */

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class AopApplication {

    public static void main (String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }
}
