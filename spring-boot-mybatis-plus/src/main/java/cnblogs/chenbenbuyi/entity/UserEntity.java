package cnblogs.chenbenbuyi.entity;

import cnblogs.chenbenbuyi.enums.Gender;
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
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("one_user")
public class UserEntity extends BaseEntity<UserEntity> {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
//    private Sex sex;
    private Gender sex;
    private String password;
    /**
     *  desc 本意指业务中的备注信息，但是这里却和数据库中的关键字排序关键字重名，会导致自动拼接的sql产生错误
     *  处理字段名字避开关键字
     */
    @TableField(value = "`desc`")
    private String desc;
}
