package cnblogs.chenbenbuyi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @date: 2020/11/3 14:27
 * @author: chen
 * @desc:
 */
@Data
@TableName("one_user")
public class UserEntity extends BaseEntity<UserEntity>{

    private Long id;
    private String username;
    private String password;

    @TableField(value="`desc`")
    private String desc;
}
