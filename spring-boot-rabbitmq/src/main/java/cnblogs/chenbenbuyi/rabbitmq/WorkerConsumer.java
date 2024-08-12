package cnblogs.chenbenbuyi.rabbitmq;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Argument;
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
 * 实测表明：
 * 1、消费者监听的情况下，在管理客户端直接删除队列，当队列重建后，消费者能正确绑定并重新监听；并且消费者存在的情况下还能自动创建起队列
 * 2、单线程消费且手动确认中，如果一条消息没有确认而超时，则会堵塞后续消息的消费
 */

@Slf4j
@Component
public class WorkerConsumer {

    private final String TASK_QUEUE = "kingkong.task.instance.queue.chenshaoxian";

    /**
     * 关于阻塞问题：在手动确认的消费模式下，如果前面的消息确认失败，后面的消息也会阻塞，不会被消费
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queuesToDeclare = @Queue(value = TASK_QUEUE, arguments = {@Argument(name = "x-queue-type", value = "classic")}), ackMode = "MANUAL")
    public void receive(Message message, Channel channel) throws InterruptedException {
        log.debug("任务消费队列消费处理任务实例为 {} 的数据===========================================", message.getBody());
        String str = StrUtil.str(message.getBody(), CharsetUtil.UTF_8);
        System.out.println(str);

        /**
         * RabbitMQ的ack机制中，第二个参数返回true，表示需要将这条消息投递给其他的消费者重新消费
         * 当我们消费失败，需要将消息重新塞入队列，等待重新消费时,可以设置第三个参数true，表示这个消息会重新进入队列
         */
        try {
            test(str);
            TimeUnit.SECONDS.sleep(20);
        } finally {
            try {
                // 消费确认
                System.out.println("消息：【" + str + "】的消费确认");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                log.error("消费确认出错：\n", e);
            }
        }
    }


    private void test(String str) {
        if (Objects.equals("5", str)) {
            int i = 1 / 0;
        }
    }
}
