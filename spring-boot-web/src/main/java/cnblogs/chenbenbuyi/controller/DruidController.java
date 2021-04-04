package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.config.DruidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2021/4/2 21:54
 * @Description 该控制器查询数据库，主要为了查看druid的监控功能
 *  详细配置见 {@link DruidConfig}
 */

@RestController
@RequestMapping("/user")
public class DruidController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    public List<Map<String, Object>> lsit() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from one_user;");
        return list;
    }
}
