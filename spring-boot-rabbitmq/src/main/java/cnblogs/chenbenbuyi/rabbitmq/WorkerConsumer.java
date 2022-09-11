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

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2021/10/4 19:52
 * @Created by csxian
 */

@Slf4j
@Component
public class WorkerConsumer {

    /**
     * 关于阻塞问题：在手动确认的消费模式下，如果前面的消息确认失败，后面的消息也会阻塞，不会被消费
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "kingkong.task.chen.queren"), ackMode = "MANUAL")
    public void receive(Message message, Channel channel) throws InterruptedException {
        String str = StrUtil.str(message.getBody(), CharsetUtil.UTF_8);
        System.out.println(str);

        /**
         * RabbitMQ的ack机制中，第二个参数返回true，表示需要将这条消息投递给其他的消费者重新消费
         * 当我们消费失败，需要将消息重新塞入队列，等待重新消费时,可以设置第三个参数true，表示这个消息会重新进入队列
         */
        try {
            try {
                if (Objects.equals("1", str)) {
                    int i = 2 / 0;
                }
                // 消费确认
            } catch (Exception e) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                System.out.println("手动构造异常！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(20);

    }
}
