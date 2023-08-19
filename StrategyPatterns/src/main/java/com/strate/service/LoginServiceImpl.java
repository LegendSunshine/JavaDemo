package com.strate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.strate.common.TypeEnum.OTHER_TYPE;

/**
 * 类名称: LoginServiceImpl
 * 类描述: 登录实现
 *
 * @author legend
 * @since 2023/7/8
 */
@Slf4j
@Service
public class LoginServiceImpl implements ILoginService{

    @Override
    public String loginType(String username) {
        Optional<String> optional = Optional.ofNullable(username);
        StringBuilder stringBuilder = new StringBuilder();
        optional.ifPresent(str-> stringBuilder.append(username).append("使用其他方式登录"));
        return stringBuilder.toString();
    }

    @Override
    public Integer getLoginType() {

        return OTHER_TYPE.getTypeCode();
    }
}
