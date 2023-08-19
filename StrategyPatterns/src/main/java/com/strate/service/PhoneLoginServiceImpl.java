package com.strate.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.strate.common.TypeEnum.PHONE_TYPE;

/**
 * 类名称: PhoneLoginServiceImpl
 * 类描述: 手机登录实现
 *
 * @author legend
 * @since 2023/7/8
 */
@Service
public class PhoneLoginServiceImpl implements ILoginService{

    @Override
    public Integer getLoginType() {
        return PHONE_TYPE.getTypeCode();
    }

    @Override
    public String loginType(String username) {
        Optional<String> optional = Optional.ofNullable(username);
        StringBuilder stringBuilder = new StringBuilder();
        optional.ifPresent(str-> stringBuilder.append(username).append("使用手机登录"));
        return stringBuilder.toString();
    }
}
