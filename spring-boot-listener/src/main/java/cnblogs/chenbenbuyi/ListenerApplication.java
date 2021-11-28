package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chen
 * @date 2021/6/13 16:10
 * @Description 测试Spring 的监听器的使用
 */
@SpringBootApplication
public class ListenerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ListenerApplication.class);
        springApplication.run(args);
    }
}
