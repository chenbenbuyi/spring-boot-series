package cnblogs.chenbenbuyi.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2021/3/30 19:40
 * @Description 通过 @ConfigurationProperties 绑定配置文件，将配置文件参数以Bean的形式在项目中使用
 */
// 只有是容器中的组件，容器才会为 @ConfigurationProperties 提供此注入功能
@Component
//  告诉 SpringBoot 将本类中的所有属性和配置文件中相关的配置进行绑定
@ConfigurationProperties(prefix = "user2")
/**
 * 指定其它配置文件，通常为了防止配置的臃肿，将一些配置独立出来使用。
 * 注意： 测试发现，如果默认配置文件如application.yml 能绑定值的话，将无法绑定 @PropertySource 引入的配置文件值
 */
@PropertySource(value = {"classpath:user.properties"})
@Data
@Validated
public class User {

    private String username;
//    可以对配置文件中的配置参数做格式化验证
    @Email(message = "邮箱格式错误")
    private String email;
    private Integer age;
    private List<String> lists;
    private Map<String, String> maps;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", lists=" + lists +
                ", maps=" + maps +
                '}';
    }
}
