package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.service.IUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @date: 2020/11/3 15:48
 * @author: chen
 * @desc: Exception：java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("单元测试")
public class MybatisPlusTest {

    @Autowired
    IUserService userService;

    @Test
    @Transactional // 测试完成之后自动回滚
    public void testList() {
        List<UserEntity> list = userService.list();
        log.info("【list】= {}", list);
    }

    @SneakyThrows
    @Test
    @Timeout(value = 3,unit = TimeUnit.SECONDS)
    public void testInsert(){
//        UserEntity user = UserEntity.builder().username("陈本布衣").sex(Sex.MALE).password("xxxxxx").desc("暂无备注信息").build();
//        boolean result = userService.saveOrUpdate(user);
//        Assert.assertTrue(result);
//        log.info("新增数据：{}", user);
        System.out.println("超时机制");
        TimeUnit.SECONDS.sleep(6);
    }
}
