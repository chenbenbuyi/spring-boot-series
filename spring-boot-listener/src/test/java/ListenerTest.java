import cnblogs.chenbenbuyi.ListenerApplication;
import cnblogs.chenbenbuyi.event.UserVo;
import cnblogs.chenbenbuyi.service.ListenerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chen
 * @date 2021/11/28 13:12
 * @SpringBootTest替代了spring-test中的@ContextConfiguration注解，目的是加载ApplicationContext，启动spring容器。
 */

@Slf4j
@SpringBootTest(classes = ListenerApplication.class)
@RunWith(SpringRunner.class)
public class ListenerTest {

    @Autowired
    ListenerServiceImpl service;

    /**
     * 实测：如果继承和注解的方式都启用的话，会导致异步失效，不明所以
     */
    @Test
    public void test() {
        UserVo userVo = new UserVo(12, "陈本布衣");
        service.publishTest1(userVo);
        log.info("事件发布之后执行。。。。。。。。。。");
    }

    @Test
    public void test2() {
        UserVo userVo = new UserVo(12, "陈本布衣");
        service.publishTest2(userVo);
        log.info("事件发布之后执行。。。。。。。。。。");
    }
}
