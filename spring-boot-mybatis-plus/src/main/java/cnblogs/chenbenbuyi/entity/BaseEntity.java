package cnblogs.chenbenbuyi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @date: 2020/9/30 10:21
 * @author: chen
 * @desc:
 */
@Data
public class BaseEntity<T extends Model<?>> extends Model<T> {

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
