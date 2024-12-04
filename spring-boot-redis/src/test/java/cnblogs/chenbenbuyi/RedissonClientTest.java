package cnblogs.chenbenbuyi;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author csxian
 * @Description RedissonClient 锁释放测试类
 * @Date 2024/12/4 14:26
 */

@SpringBootTest
public class RedissonClientTest {


    @Resource
    private RedissonClient redissonClient;


    /**
     *  以下代码演示了存在可能异常的共享释放锁方式 参考链接：https://www.cnblogs.com/youzhibing/p/18313731
     */
    @Test
    public void testWongUnLock() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                RLock lock = redissonClient.getLock("redis:lock:test");
                try {
                    boolean res = lock.tryLock(1, 20, TimeUnit.SECONDS);
                    if(res){
                        System.out.println("线程："+Thread.currentThread().getId()+"成功获取到锁"+new Date());
                        TimeUnit.SECONDS.sleep(20);
                    }else
                        System.out.println("线程："+Thread.currentThread().getId()+"获取锁失败！"+new Date());

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(lock.isLocked()){
                        lock.unlock();
                        System.out.println("线程："+Thread.currentThread().getId()+"成功释放锁"+new Date());
                    }
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(30);

    }
}
