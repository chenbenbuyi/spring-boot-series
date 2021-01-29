package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.common.MultiDB;
import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.mapper.UserMapper;
import cnblogs.chenbenbuyi.service.Db2Service;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;

/**
 * @date: 2021/1/29 15:23
 * @author: chen
 */

@Service
@DS(MultiDB.DB2)
public class Db2ServiceImpl extends BaseServiceImpl<UserMapper,UserEntity> implements Db2Service {
}
