package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author chen
 * @date 2021/4/6 20:38
 * @Description
 */
@SpringBootApplication
@EnableAsync
public class MailApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }
}
