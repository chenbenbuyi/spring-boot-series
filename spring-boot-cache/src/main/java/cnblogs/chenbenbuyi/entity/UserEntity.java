package cnblogs.chenbenbuyi.entity;

import cnblogs.chenbenbuyi.enums.Sex;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2020/11/3 14:27
 * @author: chen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("one_user")
public class UserEntity extends BaseEntity<UserEntity> {

    private Long id;
    private String username;
    private Sex sex;
    private String password;

    @TableField(value = "`desc`")
    private String desc;
}
