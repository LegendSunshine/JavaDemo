package com.strate.control;

import com.strate.config.FactoryConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/strategy")
public class LoginController {

    @PostMapping("/login")
    public  String login(Integer type,String username){

        return FactoryConfig.getLoginType(type).loginType(username);
    }
}
