package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.service.AsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void test(){
        System.out.println(asyncService.getResult());
    }
}
