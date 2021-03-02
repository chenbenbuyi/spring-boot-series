# 后端数据校验 Bean Validation 的相关测试
## 其中JSR（JSR303/SR-349）是一个规范文档，规定一些校验规范。Hibernate Validator就是基于JSR303规范的具体实现，提供了JSR 规范中内置约束注解的实现，同时附加了一些约束注解。

### 跳坑指南

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