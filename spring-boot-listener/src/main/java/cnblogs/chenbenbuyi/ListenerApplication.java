package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.listener.MyListener;
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
        // 注册监听器
        springApplication.addListeners(new MyListener());
        springApplication.run(args);
    }
}
