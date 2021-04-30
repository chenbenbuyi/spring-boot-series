package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.mapper.UserMapper;
import cnblogs.chenbenbuyi.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2020/11/3 15:35
 * @author: chen
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity> implements IUserService {
}
