# 后端数据校验 Bean Validation 的相关测试
> JSR-303 是 JAVA EE 6 中的一项子规范，叫做 Bean Validation，官方参考实现是Hibernate Validator。Bean Validation 为 JavaBean 验证定义了相应的元数据模型和API。缺省的元数据是Java Annotations
 可以 通过使用 XML 对原有的元数据信息进行覆盖和扩展。在应用程序中，通过使用Bean Validation 或是你自己定义的 constraint( [kənˈstreɪnt] 约束) ，例如 @NotNull,  就能确保数据模型（JavaBean）的正确性

#参数校验分类
## 简单校验
>直接在需要的元素上标注约束注解 ，如常用的 @NotNull，@Min，@Pattern(value)等等

## 嵌套校验
## 分组校验。


# 使用步骤
>　①为实体类中的参数或者对象添加相应的注解；
>  ②在控制器层进行注解声明，或者手动调用校验方法进行校验；
>  ③对异常进行处理；

# 跳坑指南

## 数据源自动配置导致的异常
>错误信息
>Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
    Reason: Failed to determine a suitable driver class
> 原因：在引入了Mybatis-plus依赖的情况下，程序启动会要求配置数据源信息，如果没有配置，可在启动类中排除自动配置：
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})


## jackson 反序列化错误
>错误信息：
com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `cnblogs.chenbenbuyi.model.Person` (no Creators, like default constructor, exist)
原因：jackson的反序列化需要无参构造函数

## Bad Request 400 异常
> java.lang.IllegalStateException: Could not resolve parameter [1] in xxx ... No suitable resolver
> 实测发现，是因为方法参数 添加了 HttpStatus，去掉该参数就能正常捕获异常。