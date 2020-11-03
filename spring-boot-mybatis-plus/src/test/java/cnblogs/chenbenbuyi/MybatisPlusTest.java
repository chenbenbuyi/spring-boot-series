package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * @date: 2020/11/3 15:48
 * @author: chen
 * @desc:
 * Exception：java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest  {

    @Autowired
    IUserService userService;

    @Test
    public void test(){
        log.debug("debug界别");
        log.info("info界别");
        List<UserEntity> list = userService.list();
        System.out.println(list);
    }
}
