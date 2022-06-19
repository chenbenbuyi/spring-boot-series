package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.config.WebSocketConfig;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description WebSocket 测试（通过配置 ServerEndpointExporter 的方式）
 * @Date 2022/2/20 12:22
 * @Created by csxian
 */
@Component
/**
 * 通过 @ServerEndpoint 注解暴露 ws 应用的路径，类似 @RequestMapping。
 * 比如你的启动端口是8080，而这个注解的值是chen_ws客户端就可以通过 ws://127.0.0.1:8080/chen_ws来连接你的应用
 *  该服务端点类和 {@link WebSocketConfig} 注入的bean对象相关
 *  当然，如果是使用的外部服务器启动ws的方式，是不需要配置ServerEndpointExporter对象(它将由容器自己提供和管理)和@Component注解的
 * ps:可通过在线工具测试服务端的 ws功能：https://coolaf.com/zh/tool/chattest
 */
@ServerEndpoint(value = "/chen_ws/{username}")
public class WebsocketServiceImpl2 {

    private static List<Session> sessions = new CopyOnWriteArrayList<>(); //线程安全

    /**
     * @param session  与客户端的会话对象【可选】
     * @param username 路径参数值 【可选】
     * @throws IOException
     * @OnOpen 标注此方法在 ws 连接建立时调用，可用来处理一些准备性工作 可选参数
     * EndpointConfig（端点配置信息对象） Session 连接会话对象
     */
    @OnOpen
    public void OnOpen(Session session, @PathParam("username") String username) throws IOException {
        sessions.add(session);
        sendTextMsg("好友 [" + username + "] 加入群聊");
    }

    /**
     * @param msg      接受客户端消息
     * @param username RESTful 路径方式获取用户名
     * @throws IOException
     * @OnMessage 在收到客户端消息调用 消息形式不限于文本消息，还可以是二进制消息(byte[]/ByteBuffer等)，ping/pong 消息
     */
    @OnMessage
    public void OnMsg(String msg, @PathParam("username") String username) throws IOException {
        sendTextMsg(username + "：\r\n" + msg);
    }


    /**
     * @OnClose 连接关闭调用
     */
    @OnClose
    public void OnClose(Session session, @PathParam("username") String username) throws IOException {
        sessions.remove(session);
        sendTextMsg("好友 [" + username + "] 退出群聊");
    }

    /**
     * @param e 异常参数
     * @OnError 连接出现错误调用
     */
    @OnError
    public void OnError(Throwable e) {
        e.printStackTrace();
    }

    private void sendTextMsg(String msg) {
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(msg);
        }
    }
}
