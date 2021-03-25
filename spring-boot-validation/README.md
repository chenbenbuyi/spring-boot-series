# 后端数据校验 Bean Validation 的相关测试
> JSR-303 是 JAVA EE 6 中的一项子规范，叫做 Bean Validation，官方参考实现是Hibernate Validator。Bean Validation 为 JavaBean 验证定义了相应的元数据模型和API。缺省的元数据是Java Annotations
 可以 通过使用 XML 对原有的元数据信息进行覆盖和扩展。在应用程序中，通过使用Bean Validation 或是你自己定义的 constraint( [kənˈstreɪnt] 约束) ，例如 @NotNull,  就能确保数据模型（JavaBean）的正确性

#注解参数校验分类
## 简单校验
>直接在需要的元素上标注约束注解 ，如常用的 @NotNull，@Min，@Pattern(value)等等
## 嵌套校验
> 一个对象中定义的外部对象(或其集合形式)的成员变量，外部对象同样的有参数校验时，只需在该变量上添加  @Valid 注解即可触发外部对象的参数校验
## 分组校验。
> 定义用于区分分组的空标类或接口,注解上添加分组即可，形如 @NotNull( groups = UpdateValid.class)

# 使用步骤
>　①为实体类中的参数或者对象添加相应的注解；
>  ②在控制器层进行注解声明，或者手动调用校验方法进行校验；有两种校验参数方式：
          控制器方法标注 @Valid 触发对象类型入参校验（get请求直接在相应参数前添加校验规则，形如 @Min(1) Long id， @Validated 注解才支持），然后全局异常处理或直接在控制器通过BindingResult类型参数获取异常信息处理
          手动调用对应API执行校验——Validation.buildDefault ValidatorFactory().getValidator().validate(xxx)
>  ③对异常进行处理。
    

# 国际化
> 提示信息的国际化支持在其源码包 hibernate-validator/6.1.5.Final/hibernate-validator-6.1.5.Final.jar!/org/hibernate/validator/ 目录下面，你可以看到很多关于国际化相关的提示语配置文件。根据不同语言条件下的请求，会响应不同语言配置文件中的提示语
据此思路，如果要在项目中自己实现国际化，也可以直接在自己的resources下面新建类似文件（ValidationMessages.properties等）并自定义键值对，在注解中进行取值，形如如 @NotBlank(message="{xx.xxxx.xx}")；

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