package cnblogs.chenbenbuyi.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @date: 2020/10/28 17:25
 * @author: chen
 * @desc: 普通的域对象
 */

@Data
public class User implements Serializable{

    public User(Long id) {
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
