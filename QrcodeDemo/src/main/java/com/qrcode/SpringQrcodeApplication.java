package com.qrcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * 类名称: SpringQrcodeApplication
 * 类描述: 启动类
 *
 * @author legend
 * @since 2023/08/19
 */
@SpringBootApplication
public class SpringQrcodeApplication  extends SpringBootServletInitializer {
    public static void main(String[] args)  {
        SpringApplication.run(SpringQrcodeApplication.class,args);
    }
}
