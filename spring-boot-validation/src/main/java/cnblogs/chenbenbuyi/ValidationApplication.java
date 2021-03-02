package cnblogs.chenbenbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author chen
 * @date 2021/3/2 20:42
    在引入了Mybatis-plus依赖的情况下，要求配置数据源，否则会如下启动失败：
        Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
        Reason: Failed to determine a suitable driver class
    如果暂时不想配置数据库连接信息，可排除掉数据源的自动配置
 *
 */

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ValidationApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ValidationApplication.class);
        springApplication.run(args);
    }
}
