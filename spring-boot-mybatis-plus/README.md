# 简单集成测试使用 mybatis-plus进行的增删改查以及其代码生成工具的使用
                                                                                                                   
                                                                                                                   ## 关键归纳
                                                                                                                   ## 异常归纳
                                                                                                                   ### 测试包名
                                                                                                                   >代码现象：测试类中提示相关对象无法注入
                                                                                                                   >异常抛出：java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
                                                                                                                   > 原因： 单元测试的包名要和启动类所在的包一致  
                                                                                                                   ### 日志级别
                                                                                                                   >异常抛出：org.springframework.boot.context.properties.bind.BindException: Failed to bind properties under 'logging.level'
                                                                                                                   >原因：SpringBoot2.0以上版本配置日志级别需要指定包路径
                                                                                                                   ### Mysql数据库关键字 
                                                                                                                   >原因：数据查询总是报错，排查发现因为用了desc(常用来表示备注信息字段)关键字
                                                                                                                   >解决：
                                                                                                                   >>项目初期，条件允许重命名关键字即可避免；
                                                                                                                   >>不想重命名，通过@TableField注解进行去关键化，形如@TableField(value="`desc`")。注意，直接加value="desc"没用，而是要给字段标注 `` ，表示表字段，就像你在客户端写sql时那样 