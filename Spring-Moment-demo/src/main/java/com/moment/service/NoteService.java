package com.moment.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 类名称: NoteService
 * 类描述: 即时发送短信服务类
 *
 * @author legend
 * @since 2023/7/8
 */

@Service
public class NoteService {

    /**
     * 处理流程 该方法每个整点遍历数据库，将当前时刻的数已过期要发送短信据全都查询出来，存入redis ,然后每分钟读取redis
     * 是否存在当前时刻要发送的信息，如果有就将消息取出来，然后发送
     */

    public String handle(){


        return  "获取要发送的短信";
    }

    public String redisHandle(){

        return "短信已成功发送";
    }


}
