package com.qrcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @ClassName SpringQrcodeApplication
 * @Date 2023/3/26 10:03
 * @Author legend
 */
@SpringBootApplication
public class SpringQrcodeApplication  extends SpringBootServletInitializer {
    public static void main(String[] args)  {
        SpringApplication.run(SpringQrcodeApplication.class,args);
    }
}
