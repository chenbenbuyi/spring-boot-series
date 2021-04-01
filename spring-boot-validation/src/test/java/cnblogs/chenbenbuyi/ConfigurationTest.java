package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chen
 * @date 2021/3/30 20:07
 * @Description
 */
@SpringBootTest
public class ConfigurationTest {

    @Autowired
    User user;

    @Test
    public void test(){
        System.out.println(user);
    }
}
