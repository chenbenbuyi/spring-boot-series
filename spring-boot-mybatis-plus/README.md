# 简单集成测试使用 mybatis-plus 进行的增删改查以及其代码生成工具的使用

## 关键归纳
## 踩坑指南——异常
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
>>重命名字段名字避开关键字；
>>不想重命名，通过@TableField注解进行去关键化，形如@TableField(value="`desc`")。注意，直接加value="desc"没用，而是要给字段标注 `` ，表示表字段，就像你在客户端写sql时那样
### 数据库自增id
>异常抛出：
> java.lang.IllegalArgumentException: argument type mismatch (Java程序层面)
> Data truncation: Out of range value for column 'id' at row 1; (数据库层面)
>原因：两种异常抛出的根源大致相同——MyBatis-Plus(MP)主键默认生成策略为雪花算法，会生成全局唯一但又变态大的id，通常Long以下的数据类型是无法存的 ps:这里要厘清系统中有两个地方的Id增长策略：
一个是应用程序本身的映射模型(如UserEntity)数据初始化时的字段生成策略，如持久层框架的主键生成策略、自定义的生成策略(如随机数)等，一个是数据库自身的主键生成策略，是次序自增还是靠程序插入等
>解决：根据上面的思想，问题很好解决，有几种思路
> 将就MP默认的主键生成策略，更改自己的数据模型的字段类型为Long，数据库相应的需要更改位比较大的BIGINT类型；当然，一般小系统，无意义而且存储空间过大的id，相比不会这么干
> 自己选择合适的id适生成策略生产合大小的id,比如ID由程序根据业务生成。框架层面不做任何更改。(MP还是很智能的，你自己都产生ID了，也不会给你画蛇添足)
> 程序干脆放弃ID生成，由数据库自己产生，如自增，MP框架层面进行相应的局部或全局配置

    /**
     *  附上MP主键策略解析：
     *  IdType.AUTO 数据库ID自增
     *  IdType.NONE  跟随全局 如果全局为 NONE配置，差不多就相当于放弃主键生成，由调用者控制主键产生（等同于 INPUT）
     *  IdType.INPUT  调用者产生
     *  IdType.ASSIGN_ID  雪花算法
     *  IdType.ASSIGN_UUID  uuid
     *  
     *  其它已废弃
     */
> 本程序示例中，在数据库已经设置id自增的情况喜爱，可通过 @TableId 局部设置或在yml文件中配置全局的，none,auto,input都可以,只不过none或input无法获取到新插入数据的id值
mybatis-plus:
  global-config:
    db-config:
      id-type: auto
      
## 在阿里巴巴开发手册第六部分工程规约中，有这么一条：
> 二方库里可以定义枚举类型，参数可以使用枚举类型，但是接口返回值不允许使用枚举类型或者包含枚举类型的 POJO 对象。
>>由于升级原因，导致双方的枚举类不尽相同，在接口解析，类反序列化时出现异常。
>> 这里也正是辨析枚举使用的好处和劣势的好时机：好处，类型安全，清晰直接，还可以使用等号来判断，也可以用在switch中。
>> 最明显的劣势，枚举基于固定常量值来定义的，扩展或更改是有违开闭原则的。你必须去改动枚举常量，这也就意味这依赖该枚举实例的所有代码都有改动引起的稳定性风险。
参考：https://www.zhihu.com/question/52760637