package cnblogs.chenbenbuyi;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2020/11/25 21:54
 * @Description 单元测试
 */

@DisplayName("Junit单元测试")
public class ChenTest {


    @BeforeAll
    @Test
    public static void beforeAll() {
        System.out.println("必须是静态方法，在所有方法开始之前，而不是每个方法开始之前");
    }

    @DisplayName("BeforeEach")
    @BeforeEach
    @Test
    public void beforeEach() {
        System.out.println("每个测试方法运行之前都会运行");
    }

    @DisplayName("Disabled注解测试")
    @Disabled
    @Test
    public void Disabled() {
        System.out.println("测试方法禁用");
    }

    @SneakyThrows
    @Timeout(value = 3)
    @DisplayName("超时测试")
    @Test
    public void timeOut() {
        System.out.println("超时测试");
        TimeUnit.SECONDS.sleep(6);
    }


    @Test
    @RepeatedTest(4)
    @DisplayName("RepeatedTest重复注解测试")
    public void repeatedTest() {
        System.out.println("RepeatedTest重复注解测试");
    }


    /**
     * 参数化测试
     */

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testParam(int i) {
        System.out.println(i);
    }


    // 从指定方法中获取入参
    @ParameterizedTest
    @MethodSource("val")
    public void testParam2(String s) {
        System.out.println(s);
    }

    public static String[] val() {
        return new String[]{"返回参数","返回参数2"};
    }

}
