package com.sign.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */

@Service
public class SignService {
    public Object add(String name, String age) {
        System.out.println("操作数据库"+name+"年龄:"+age);
        return "成功";
    }
}
