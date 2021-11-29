package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class XiaoyuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoyuanApplication.class, args);
    }
}