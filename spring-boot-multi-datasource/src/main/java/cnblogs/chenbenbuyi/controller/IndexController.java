package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.Result;
import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.service.Db1Service;
import cnblogs.chenbenbuyi.service.Db2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * @date: 2021/1/29 14:33
 * @author: chen
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    DataSource dataSource;

    @Autowired
    Db1Service db1Service;
    @Autowired
    Db2Service db2Service;

    @GetMapping("/db1")
    public Result<List<UserEntity>> db1() {
        return Result.ok(db1Service.list());
    }

    @GetMapping("/db2")
    public Result<List<UserEntity>> db2() {
        return Result.ok(db2Service.list());
    }

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
