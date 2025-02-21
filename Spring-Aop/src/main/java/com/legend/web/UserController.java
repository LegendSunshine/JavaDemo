package com.legend.web;

import com.legend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className UserController
 * @description:
 * @author legend
 * @date 2025/1/2 19:36
 * @version 1.0
 */
@RequestMapping("/v1/user/")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("findUserInfo")
    public void findUserInfo(){
     Integer userId  =  userService.findUserInfo();
    }
}
