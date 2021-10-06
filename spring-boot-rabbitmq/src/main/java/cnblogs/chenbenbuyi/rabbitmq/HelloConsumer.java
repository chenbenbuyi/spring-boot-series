package cnblogs.chenbenbuyi.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Date 2021/10/4 19:52
 * @Created by csxian
 */

@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello",durable = "false",autoDelete = "false"))
public class HelloConsumer {

    @RabbitHandler
    public void hello(String message) {
        System.out.println("收到的消息队里中的消息：" + message);
    }
}
