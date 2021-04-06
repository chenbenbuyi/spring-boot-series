package cnblogs.chenbenbuyi.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2021/4/6 20:40
 * @Description 异步测试方法
 */
@Service
public class AsyncService {

    /**
     *  异步任务开启很简单：
     *      1 在启动方法上标注 @EnableAsync 开启异步支持
     *      2 目标方法标注异步注解 @Async
     */
    @Async
    public String getResult(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "延时返回结果";
    }
}
