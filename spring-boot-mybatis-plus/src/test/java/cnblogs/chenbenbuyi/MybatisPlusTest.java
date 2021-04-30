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
 * @desc: Exception：java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    IUserService userService;

    @Test
    public void testList() {
        List<UserEntity> list = userService.list();
        log.info("【list】= {}", list);
    }

    @Test
    public void testInsert(){
//        UserEntity user = UserEntity.builder().username("陈本布衣").sex(Sex.MALE).password("xxxxxx").desc("暂无备注信息").build();
//        boolean result = userService.saveOrUpdate(user);
//        Assert.assertTrue(result);
//        log.info("新增数据：{}", user);
    }
}
