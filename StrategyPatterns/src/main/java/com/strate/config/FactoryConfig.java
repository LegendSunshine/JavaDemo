package com.strate.config;

import com.strate.service.ILoginService;
import javafx.application.Application;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称: FactoryConfig
 * 类描述: 工厂配置类
 *
 * @author legend
 * @since 2023/7/8
 */

@Component
public class FactoryConfig implements InitializingBean,ApplicationContextAware {

    private ApplicationContext application;

    // 将不同类型的实现类放到map 中 key 时实现类的类型， value是实现类

    private static final Map<Integer, ILoginService> LOGIN_MAP = new HashMap<>(16);

    /**
     * 获取登录类型
     *
     * @param type 类型
     * @return {@link ILoginService}
     */
    public static ILoginService getLoginType(Integer type) {
        if (type == null) {
            throw new IllegalArgumentException("类型不能是空");
        }
        if (!LOGIN_MAP.containsKey(type)) {
            throw new IllegalArgumentException("该类型不存在");
        }
        return LOGIN_MAP.get(type);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //获取某一接口的所有实现类
        application.getBeansOfType(ILoginService.class).values().forEach(map -> LOGIN_MAP.put(map.getLoginType(),map));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        application = applicationContext;
    }
}
