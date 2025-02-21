package com.legend.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * @className UserService
 * @description:
 * @author legend
 * @date 2025/1/2 19:37
 * @version 1.0
 */

@Service
public class UserService {

    Log log = LogFactory.getLog(UserService.class);
    public Integer findUserInfo() {
    log.info("findUserInfo:"+"开始执行方法");
        return 1;
    }
}
