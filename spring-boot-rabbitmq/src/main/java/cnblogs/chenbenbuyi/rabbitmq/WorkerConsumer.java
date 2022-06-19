package cnblogs.chenbenbuyi.rabbitmq;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cnblogs.chenbenbuyi.model.UpdateTaskStatusDTO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2021/10/4 19:52
 * @Created by csxian
 */

@Slf4j
@Component
public class WorkerConsumer {

    @RabbitListener(queuesToDeclare = @Queue(value = "kingkong.task.dts.queue.test"), ackMode = "MANUAL")
    public void receive(Message message, Channel channel) throws InterruptedException {
        String str = StrUtil.str(message.getBody(), CharsetUtil.UTF_8);
        System.out.println(str);

//        int i = 1 / 0;
//        // RabbitMQ的ack机制中，第二个参数返回true，表示需要将这条消息投递给其他的消费者重新消费
//        // 当我们消费失败，需要将消息重新塞入队列，等待重新消费时,可以设置第三个参数true，表示这个消息会重新进入队列
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(30);
//        channel.basicAck(deliveryTag, false);

    }
}
