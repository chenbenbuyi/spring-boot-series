package cnblogs.chenbenbuyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2020/11/3 14:27
 * @author: chen
 * @desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("one_user")
public class UserEntity extends BaseEntity<UserEntity>{

    private Integer id;
    private String username;
    private String password;

    @TableField(value="`desc`")
    private String desc;
}
