package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.pojo.User;
import cnblogs.chenbenbuyi.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chen
 * @date 2021/4/4 18:49
 * @Description
 */

@SpringBootTest
public class DubboTest {

    @Autowired
    ConsumerService consumerService;

    @Test
    public void test(){
        User user = consumerService.getOne(100L);
        System.out.println(user);
    }
}
