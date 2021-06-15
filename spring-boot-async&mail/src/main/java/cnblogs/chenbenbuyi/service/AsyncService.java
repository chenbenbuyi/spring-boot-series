package cnblogs.chenbenbuyi.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2021/4/6 20:40
 * @Description 异步测试方法
 * 异步任务开启很简单：
 *  1 在启动方法上标注 @EnableAsync 开启异步支持
 *  2 目标方法标注异步注解 @Async
 * 常见的异步场景分为两种：
 *  1、简单的异步调用，没有返回值
 *  2、异步调用有返回值，通过Future接受返回值
 */
@Service
@Slf4j
public class AsyncService {

    /**
     * 异步调用，无返回值
     */
//    @Async("asyncTaskExecutor")
    @Async
    @SneakyThrows
    public void asyncTask() {
//        int i =1/0;
        log.info("执行异步任务asyncTask,当前执行线程："+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 异步调用，有返回值
     */
    @SneakyThrows
    @Async
    public Future<String> asyncTask(String s) {
//        int i =1/0;
        log.info("执行异步任务asyncTask2,当前执行线程："+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
        return new AsyncResult<>("你的输入参数：" + s);
    }

    /**
     * 异步调用，无返回值，事务测试
     */
    public void asyncTaskForTransaction(Boolean exFlag) {

    }
}
