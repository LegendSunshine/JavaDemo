package com.example.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类名称:
 * 类描述: 启动类，NacosPropertySource 注解注册服务
 *
 * @author legend
 * @since 2023/7/8
 */
@NacosPropertySource(dataId = "example",autoRefreshed = true)
@SpringBootApplication
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class,args);
    }
}
