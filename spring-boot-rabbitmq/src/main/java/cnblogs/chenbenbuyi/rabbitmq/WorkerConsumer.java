package cnblogs.chenbenbuyi.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Date 2021/10/4 19:52
 * @Created by csxian
 */

@Component
public class WorkerConsumer {

    @RabbitListener(queuesToDeclare = @Queue(value = "work",durable = "false",autoDelete = "false"))
    public void receive(String message) {
        System.out.println("任务模型-消费者 1 收到的消息：" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work",durable = "false",autoDelete = "false"))
    public void receive2(String message) {
        System.out.println("任务模型-消费者 2 收到的消息：" + message);
    }
}
