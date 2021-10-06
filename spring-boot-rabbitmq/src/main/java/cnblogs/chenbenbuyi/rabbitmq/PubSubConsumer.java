package cnblogs.chenbenbuyi.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description 广播模型
 * @Date 2021/10/4 20:19
 * @Created by csxian
 */

@Component
public class PubSubConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 如果不指定队列，就是创建的临时队列
                    exchange=@Exchange(
                            value = "log-exchange",type = "fanout"
                    ))
    })
    public void receive(String message) {
        System.out.println("发布订阅模型 1 收到的消息：" + message);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange=@Exchange(
                            value = "log-exchange",type = "fanout"
                    ))
    })
    public void receive2(String message) {
        System.out.println("发布订阅模型 2 收到的消息：" + message);
    }
}
