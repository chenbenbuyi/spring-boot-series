package cnblogs.chenbenbuyi.util;

import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2021/4/10 21:14
 */
public class SleepUtil {

    public static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
