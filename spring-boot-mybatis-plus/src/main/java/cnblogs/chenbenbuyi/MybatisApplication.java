package cnblogs.chenbenbuyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @date: 2020/11/2 10:39
 * @author: chen
 * @desc:
 */

@MapperScan("cnblogs.chenbenbuyi.mapper")
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
