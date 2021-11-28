package cnblogs.chenbenbuyi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务线程池的配置
 * 默认情况下，事件的发布和监听操作是同步执行的，可以配置  @Async 注解异步的执行
 * 此处配置自定义的线程池取代默认的线程池
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
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 异常处理配置 实测表明，该回调只能捕获无返回值的异步任务抛出的异常
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    /**
     * 自定义异常处理类
     */
    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            log.info("-------------》》》捕获线程异常信息");
            log.info("Exception message - " + throwable.getMessage());
            log.info("Method name - " + method.getName());
            for (Object param : obj) {
                log.info("Parameter value - " + param);
            }
        }

    }


//    @Bean("asyncTaskExecutor")
//    public Executor asyncTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setMaxPoolSize(MAX_POOL_SIZE);
//        executor.setCorePoolSize(CORE_POOL_SIZE);
//        executor.setQueueCapacity(1);
//        executor.setThreadNamePrefix("async-task-thread-pool-");
//        executor.initialize();
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
//        return executor;
//    }
}