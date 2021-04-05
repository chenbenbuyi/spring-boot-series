package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.entity.UserEntity;

import java.io.Serializable;

/**
 * @date: 2020/11/3 15:34
 * @author: chen
 */
public interface IUserService extends IBaseService<UserEntity> {

    UserEntity getOneById(Serializable id);

    boolean delById(Serializable id);

    UserEntity updateUser(UserEntity user);
}
