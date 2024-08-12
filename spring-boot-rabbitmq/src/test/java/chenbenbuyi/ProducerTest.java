package chenbenbuyi;

import cnblogs.chenbenbuyi.RabbitApplication;
import com.rabbitmq.client.GetResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2021/10/4 19:43
 * @Created by csxian
 */
@Slf4j
@SpringBootTest(classes = RabbitApplication.class)
@RunWith(SpringRunner.class)
public class ProducerTest {
    private static final String EXCHANGE_NAME = "log";
    @Autowired
    RabbitTemplate rabbitTemplate;


    // hello world 模型
    @Test
    public void test1() {
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend("kingkong.task.instance.chenshaoxian", "测试hello world 消息发送:" + i);
        }
    }


    // 任务模型
    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "工作队列模型 消息发送：" + i);
        }
    }

    // 发布订阅模型
    @Test
    public void test3() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", "发布订阅 消息发送");
    }

    // 路由模型
    @Test
    public void test4() {
        // 要拉取的消息条数
        int batchSize = 2;
        rabbitTemplate.execute(
                channel -> {
                    List<SampleMessage> messageBodyList = new ArrayList<>(batchSize);
                    long deliveryTag = 0;

                    while (messageBodyList.size() < batchSize) {
                        GetResponse response = channel.basicGet("kingkong.task.instance.queue.chenshaoxian", false);
                        log.info("The result of pulling the message response this time: {}", response);
                        if (Objects.isNull(response)) {
                            break;
                        }

                        byte[] bodyBytes = response.getBody();
                        String json = new String(bodyBytes);
                        deliveryTag = response.getEnvelope().getDeliveryTag();
                    }

                    try {
                        // 可以在此做一些业务操作
                        // 也可以将此消费者测试用例封装起来，用java.util.function.Consumer作为参数传入，在此进行consumer.accept(messageBodyList)，以确保业务操作成功之后ack

                        channel.basicAck(deliveryTag, true);
                    } catch (Exception e) {
                        log.error("Abnormal consumption!", e);

                        try {
                            channel.basicNack(deliveryTag, true, true);
                        } catch (Exception ex) {
                            log.error("Reject message exception!", ex);
                        }
                    }

                    // Or you can return the result to handle it yourself,
                    // but you must ensure that the ack will not affect you
                    return null;
                });

        System.out.println("================");
    }

    @Data
    private static class SampleMessage {
        private int id;
        private String name;
    }
}
