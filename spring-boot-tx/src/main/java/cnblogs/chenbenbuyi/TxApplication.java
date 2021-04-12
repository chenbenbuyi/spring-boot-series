package cnblogs.chenbenbuyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chen
 * @date 2021/4/10 15:37
 * @Description
 */
@SpringBootApplication
@MapperScan(basePackages = "cnblogs.chenbenbuyi.mapper")
public class TxApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class, args);
    }
}
