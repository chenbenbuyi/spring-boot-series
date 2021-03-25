package cnblogs.chenbenbuyi.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chen
 * @date 2021/3/25 21:56
 * @Description
 */

@Data
public class SerialUser implements Serializable {


    public SerialUser(Long id) {
        this.id = id;
        this.name = "测试默认用户";
        this.addr = "测试默认地址";
        this.age = 100;
    }

    private Long id;
    private String name;
    private String addr;
    private Integer age;
}
