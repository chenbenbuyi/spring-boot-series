package cnblogs.chenbenbuyi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chen
 * @date 2021/4/11 18:43
 * @Description 绑定配置文件属性
 */

@ConfigurationProperties(prefix = "diy.chen")
public class DemoProperties {

    private String name = "chen";
    private String address = "四川-简阳";
    private Integer age = 18;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return name + "-->" + address + "-->" + age;
    }
}
