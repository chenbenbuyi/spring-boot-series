package cnblogs.chenbenbuyi.entity;

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
@TableName("one_department")
public class DepartmentEntity  {

    private Long id;
    private String name;
}
