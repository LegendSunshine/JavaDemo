package com.strate.control;

import com.strate.config.FactoryConfig;
import com.strate.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
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
public class LoginController {

    @PostMapping("/login")
    public  String login(Integer type,String username){

        return FactoryConfig.getLoginType(type).loginType(username);
    }
}
