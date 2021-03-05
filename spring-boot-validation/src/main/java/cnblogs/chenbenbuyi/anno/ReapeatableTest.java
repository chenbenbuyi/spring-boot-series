package cnblogs.chenbenbuyi.anno;

import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

/**
 * @author chen
 * @date 2021/3/6 0:17
 */

@MyReapeatable("可重复1")
@MyReapeatable("可重复2")
public class ReapeatableTest {

    /**
     *  获取可重复注解
     */
    public static void main(String[] args) {
        MyReapeatable.Container annotation = ReapeatableTest.class.getAnnotation(MyReapeatable.Container.class);
        MyReapeatable[] values = annotation.value();
        Arrays.stream(values).forEach(identity ->
                System.out.println(identity.value()));
    }
}
