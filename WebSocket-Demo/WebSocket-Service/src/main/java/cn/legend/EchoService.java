package cn.legend;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @className EchoService
 * @description:
 * @author legend
 * @date 2026/1/29 22:44
 * @version 1.0
 */

/*
* @ServerEndpoint 指定 WebSocket 端点的 URL
* */
@ServerEndpoint("/echo")
public class EchoService {

    @OnOpen
    public void onOpen(Session session){
        System.out.println("服务器：WebSocket 连接已经建立。");
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.print("服务器收到消息："+message);
        try {
            session.getBasicRemote().sendText("服务器收到消息");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @OnClose
    public void onClose() {
        System.out.println("WebSocket 连接已经关闭。");
    }


    @OnError
    public void onError(Throwable throwable){
        System.out.printf("链接出现错误");
    }

}
