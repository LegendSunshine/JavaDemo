package com.strate.service;

/**
 * 类名称: ILoginService
 * 类描述: iLogin代理服务
 *
 * @author legend
 * @since 2023/08/19
 */
public interface ILoginService {

    public String loginType(String username);

    public Integer getLoginType();
}
