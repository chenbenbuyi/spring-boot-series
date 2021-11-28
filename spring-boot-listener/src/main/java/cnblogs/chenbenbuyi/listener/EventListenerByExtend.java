package cnblogs.chenbenbuyi.listener;

import cnblogs.chenbenbuyi.event.UserEventSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2021/6/13 16:17
 * @Description 事件监听器，监听事件源发起事件
 */
@Slf4j
@Component
public class EventListenerByExtend implements ApplicationListener<UserEventSource> {


    /**
     * 如果业务数据的操作涉及数据库事务，可以使用@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)注解开启事务监听，
     * 确保数据入库后再进行监听器的操作。
     * ，如果发射事件的地方没有事务，这里需要在注解中加一个参数，如：@TransactionalEventListener(fallbackExecution = true)
     * 如果一个事件源注册了多个事监听器，在同步执行的情况下，@order 注解的值越小，执行顺序越前
     */

    @SneakyThrows
    @Override
    @Async
    @Order(3)
    public void onApplicationEvent(UserEventSource event) {
        log.info("继承的方式，用户注册事件监听1：" + event.getUserVo());
        TimeUnit.SECONDS.sleep(4);
    }


}
