package cnblogs.chenbenbuyi.config;

import cnblogs.chenbenbuyi.service.MyWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description 推荐的配置方式（该方式可以配置websocket入口，允许访问的域、注册Handler、定义拦截器等等等）
 * @Date 2022/2/20 15:04
 * @Created by csxian
 */
@EnableWebSocket
@Configuration
public class WebSocketConfig2 implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/ws/serverTwo")//设置连接路径和处理
                .setAllowedOrigins("*")
                .addInterceptors(new MyWebSocketInterceptor());//设置拦截器
    }

    /**
     * 自定义拦截器拦截WebSocket请求
     * 拦截器,可以在连接进入到Handler处理时进行一些操作，比如从session中拿出用户登陆信息作为唯一标识符等等
     */
    class MyWebSocketInterceptor implements HandshakeInterceptor {
        //前置拦截一般用来注册用户信息，绑定 WebSocketSession
        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
            if (!(request instanceof ServletServerHttpRequest)) return true;
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
//            String userName = (String) servletRequest.getSession().getAttribute("userName");
            HttpSession session = servletRequest.getSession();
            System.out.println("前置拦截,session 的哈希值："+session.hashCode());
            attributes.put("userName", "sfsdfdsf");
            return true;
        }

        @Override
        public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Exception exception) {
            System.out.println("后置拦截~~");
        }
    }
}
