package cnblogs.chenbenbuyi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @date: 2020/10/28 17:25
 * @author: chen
 * @desc: 普通的域对象
 */

@ApiModel("用户实体对象")
@Data
public class User {

    public User(Long id) {
        this.id = id;
        this.name = "测试默认用户";
        this.addr = "测试默认地址";
        this.age = 100;
    }

    /**
     * 经测试类上标注 @ApiModel 注解，则默认所有的属性都会显示，可配置hidden属性显示的隐去
     */
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "用户地址")
    private String addr;
    @ApiModelProperty(value = "用户名年龄")
    private Integer age;
}
