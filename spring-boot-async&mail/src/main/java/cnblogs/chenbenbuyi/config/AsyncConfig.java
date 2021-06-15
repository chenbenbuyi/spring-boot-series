package cnblogs.chenbenbuyi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Spring 中默认的异步任务线程池是{@link SimpleAsyncTaskExecutor}，该线程池很坑的一点就是——默认情况下，来一个任务创建一个线程，完全没有达到线程复用的目的，所以通常情况下，需要自定义线程池
 * 推荐使用的是{@link ThreadPoolTaskExecutor},该线程池是对jdk原生线程池对象的一个包装
 * 以下即通过自定义配置代替默认的线程池，不过该配置为全局配置，意味着会除非异步注解单独指定，否则会适用于所有的异步注解任务
 * 测试拒绝策略发现:线程提交发生拒绝后，在队列为 0 的情况下，线程会一直挂起
 *
 *
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    private static final int MAX_POOL_SIZE = 1;
    private static final int CORE_POOL_SIZE = 1;

    /**
     * 自定义配置线程池
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(0); // 设置 0 会使用 同步移交队列
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("AsyncTask-pool-");
        executor.setRejectedExecutionHandler((r, executor1) -> {
            log.warn("当前任务线程池队列已满.");
        });
        executor.initialize();
        return executor;
    }

    /**
     * 异常处理配置 实测表明，该回调只能捕获无返回值的异步任务抛出的异常
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> log.error("线程池执行任务发生未知异常.", ex);
    }


    @Bean("asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setQueueCapacity(1);
        executor.setThreadNamePrefix("async-task-thread-pool-");
        executor.initialize();
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return executor;
    }
}
