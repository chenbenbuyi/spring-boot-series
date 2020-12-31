package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @date: 2020/12/31 14:04
 * @author: chen
 * @desc:
 */
@RestController
public class Db1Controller {

    /**
     *  注入数据源对象进行查验
     */
    @Resource
    DataSource dataSource;

    @GetMapping
    public Result index() {
        try {
            Connection connection = dataSource.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("数据源>>>>>>" + dataSource.getClass());
            System.out.println("连接>>>>>>>>" + connection);
            System.out.println("连接地址>>>>" + connection.getMetaData().getURL());
            System.out.println("驱动名称>>>>" + metaData.getDriverName());
            System.out.println("驱动版本>>>>" + metaData.getDriverVersion());
            System.out.println("数据库名称>>" + metaData.getDatabaseProductName());
            System.out.println("数据库版本>>" + metaData.getDatabaseProductVersion());
            System.out.println("连接用户名称>" + metaData.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
}
