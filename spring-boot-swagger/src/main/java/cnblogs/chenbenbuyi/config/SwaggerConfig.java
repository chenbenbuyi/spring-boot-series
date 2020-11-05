package cnblogs.chenbenbuyi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

@Configuration
/** Swagger 配置类
 *  开启Swagger 自动配置 .有了改注解其实启动该项目访问就已经有swagger界面了，表示集成OK,只是缺少详细配置和需要访问的API
 */
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 指定构建api文档的详细信息的方法：apiInfo()
                .apiInfo(apiInfo())
                .groupName("测试组1")
                //Swagger开关，默认是true，配置false表示关闭Swagger功能，浏览器将无法访问，
                .enable(true)
                // 启动api选择的构建器。调用该方法其实就是将当前正在构造对象的引用从Docket变成了ApiSelectorBuilder 。详情见源码和Javadoc
                .select()
                // 过滤条件，示例：指定需要生成 Api 接口的限定条件，如特定的包路径和特定的注解 根据需要配置一个或多个。最终显示为过滤条件的交集
                .apis(RequestHandlerSelectors.basePackage("cnblogs.chenbenbuyi.controller"))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any()) 默认值
                // 过滤条件：指定哪些请求路径的Api会被显示
                .paths((String url) -> Objects.requireNonNull(url).startsWith("/swagger"))
                .build();
    }

    /**
     *  新增该实例只是为了演示分组
     *  基于不同的过滤条件，采取不同的分组，方便页面进行模块化的展示。
     *  根据该实例中的 测试组2，你在页面将只能看到请求地址为/swagger2开头的Api信息,而 测试组1 则会显示所有
     */
    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("测试组2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cnblogs.chenbenbuyi.controller"))
                .paths((String url) -> Objects.requireNonNull(url).startsWith("/swagger2"))
                .build();
    }

    /**
     * 设置api文档的详细信息配置
     * 自行跟踪源码，可以很容易的查看可构造哪些具体配置信息，此处尽量完备展示
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("博客园：陈本布衣", "https://www.cnblogs.com/chenbenbuyi/", "保密");
        return new ApiInfoBuilder()
                .title("Spring Boot集成Swagger2")
                .description("这里填写一段描述信息")
                .contact(contact)
                .version("1.0")
                .license("The MIT License")
                .licenseUrl("http://www.opensource.org/licenses/MIT")
                .extensions(new ArrayList<>(Collections.singletonList(new StringVendorExtension("扩展信息", "扩展信息值"))))
                // 构建
                .build();
    }
}