package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.mapper.UserMapper;
import cnblogs.chenbenbuyi.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @date: 2020/11/3 15:35
 * @author: chen
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity> implements IUserService {
}
