package cnblogs.chenbenbuyi.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2021/4/11 19:21
 * @Description 实测表明，@Scheduled 默认情况下，无论在一个类中还是在多个类中，都是单线程执行，也就意味着，一个或者其它类的的方法，在执行时序上会相互影响——
 * 即当前方法的任务如果没有执行完下一个定时方法任务想要执行，也要等当前方法任务执行完成后才能继续执行
 */

@Service
public class ScheduledService {

    /**
     * cron 表达式
     */
    @Scheduled(cron = "0/20 * * * * ?")
    public void hello(){
        System.out.println("ScheduledService定时任务，我在执行"+Thread.currentThread().getName()+"时间："+new Date());
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
