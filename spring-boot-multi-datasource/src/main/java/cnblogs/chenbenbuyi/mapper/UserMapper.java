package cnblogs.chenbenbuyi.mapper;

import cnblogs.chenbenbuyi.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @date: 2021/1/29 15:24
 * @author: chen
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
