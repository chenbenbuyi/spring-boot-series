package cnblogs.chenbenbuyi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author chen
 * @date 2021/4/10 15:53
 * @Description
 */

@Data
@TableName("tx")
public class Tx {
    private Integer id;
    private String username;
    private Integer money;
}
