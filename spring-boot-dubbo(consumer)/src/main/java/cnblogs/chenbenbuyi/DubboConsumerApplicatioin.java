package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author chen
 * @date 2021/4/4 17:26
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DubboConsumerApplicatioin {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplicatioin.class,args);
    }
}
