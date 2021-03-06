package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @date: 2020/10/29 11:39
 * @author: chen
 * @desc:
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
