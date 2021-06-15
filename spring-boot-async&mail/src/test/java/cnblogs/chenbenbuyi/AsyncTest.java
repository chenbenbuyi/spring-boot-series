package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.service.AsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author chen
 * @date 2021/4/6 20:42
 * @Description
 */

@SpringBootTest
public class AsyncTest {
    @Autowired
    AsyncService asyncService;


    @Test
    public void test() throws ExecutionException, InterruptedException {
        asyncService.asyncTask();
        asyncService.asyncTask();
        Future<String> future = asyncService.asyncTask("我是小仙！");
        System.out.println("任务提交完成");
        System.out.println(future.get());
    }
}
