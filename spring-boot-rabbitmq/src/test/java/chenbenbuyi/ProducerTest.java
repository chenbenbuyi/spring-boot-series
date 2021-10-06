package chenbenbuyi;

import cnblogs.chenbenbuyi.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Date 2021/10/4 19:43
 * @Created by csxian
 */
@SpringBootTest(classes = RabbitApplication.class)
@RunWith(SpringRunner.class)
public class ProducerTest {
    private static final String EXCHANGE_NAME = "log";
    @Autowired
    RabbitTemplate rabbitTemplate;


    // hello world 模型
    @Test
    public void test1() {
        rabbitTemplate.convertAndSend("hello","测试hello world 消息发送");
    }


    // 任务模型
    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","工作队列模型 消息发送："+i);
        }
    }

    // 发布订阅模型
    @Test
    public void test3() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"","发布订阅 消息发送");

    }

    // 路由模型
    @Test
    public void test4() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"error","发布订阅 消息发送");

    }
}
