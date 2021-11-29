package cnblogs.chenbenbuyi.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author chen
 * @date 2021/11/29 20:52
 * @Description
 */

@Service
public class RetryServiceImpl {

    /**
     * 参数说明
     * value 抛出指定异常后执行重试
     * maxAttempts 最大重试次数 默认 3 次
     * backoff：重试等待策略，
     * exclude 排除不处理的异常
     * multiplier 延迟指定的倍数，比如delay=5000L,multiplier=2时，第一次重试为5秒后，第二次为10秒，第三次为20秒
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000L, multiplier = 2))
    public void retry() {
        System.out.println("重试测试的打印语句，如果我不断打印，说明抛异常了");
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @Recover 当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行重试失败后最终的处理。
     *  需要注意的是发生的异常和入参类型一致时才会回调
     */
    @Recover
    public void recover(Exception e) {
        System.out.println("重试失败，该方法被调用:" + e.getMessage());
    }
}
