package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2021/4/2 21:11
 * @Description
 */
@SpringBootTest
public class SpringDataJdbcTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    DemoService demoService;

    /**
     *  默认 数据源为com.zaxxer.hikari.HikariDataSource
     *  引入 druid 替换type 之后就变成了 DruidDataSource
     */
    @Test
    public void test1() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void test2() throws SQLException {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from one_user;");
        System.out.println(maps);
    }



    @Test
    public void test3() throws SQLException {
        demoService.print();
    }

}
