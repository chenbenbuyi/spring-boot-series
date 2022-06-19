//package cnblogs.chenbenbuyi.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Description 路由模型
// * @Date 2021/10/4 20:31
// * @Created by csxian
// */
//
//@Component
//public class RouteConsumer {
//
//
//    @RabbitListener(bindings = {
//            @QueueBinding(
//                    value = @Queue,
//                    exchange=@Exchange(
//                            value = "log",type = "direct"
//                    ),
//                    key={"info"}
//            ),
//    })
//    public void receive(String message) {
//        System.out.println("info路由 收到的消息：" + message);
//    }
//
//    @RabbitListener(bindings = {
//            @QueueBinding(
//                    value = @Queue,
//                    exchange=@Exchange(
//                            value = "log",type = "direct"
//                    ),
//                    key={"error"}
//            ),
//    })
//    public void receive2(String message) {
//        System.out.println("error路由 收到的消息：" + message);
//    }
//}
