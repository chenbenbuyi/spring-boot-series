package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2021/4/11 19:15
 * @Description
 *
 */

@SpringBootApplication
@EnableScheduling // 开启定时功能支持
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

    /**
     * 基于注解(@Scheduled) 的定时任务，使用简单.属性理解：
     * cron：配置cron表达式
     * fixedRate 上一次任务的开始时间到下一次任务的开始时间
     * fixedDelay 的间隔是前次任务的结束与下次任务的开始，即前一次任务完成后，延时固定长度然后执行下一次任务
     */
//    @Scheduled(cron = "0/5 * * * * ?")  // 利用表达式指定执行周期 每 5 秒
//    @Scheduled(fixedRate = 5000) //指定时间间隔，任务会在上一次执行完毕之后5秒再执行,如果任务执行时间过长，执行间隔周期相应延长
    @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行，注意是任务执行完之后，方法调度周期是延迟时间fixedDelay加任务阻塞时间
    //@Scheduled(initialDelay = 2000, fixedDelay = 5000) //第一次延迟2秒后执行，之后按fixedDelay的规则每5秒执行一次
    private void configureTasks() {
        System.out.println("执行静态定时任务时间: " + LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     *  基于接口
     */
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                // 1、添加要执行的任务
                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
                ///2 、设置触发器
                triggerContext -> {
                    // 具体执行周期的表达式可以从数据库、配置文件等多种渠道获取
                    String cron = "0/5 * * * * ?";
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
