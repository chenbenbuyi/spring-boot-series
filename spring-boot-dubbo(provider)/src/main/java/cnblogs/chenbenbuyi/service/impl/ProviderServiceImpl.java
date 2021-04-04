package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.pojo.User;
import cnblogs.chenbenbuyi.service.IProviderService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author chen
 * @date 2021/4/4 17:31
 *
 *   通常的较为旧的版本都是用的@Service 注解,在本模块引入的较新（2.7.7开始）的版本中，该注解已废弃，估计是回避和Spring的注解名冲突
 *   在较旧的版本依赖中，注意区分
 */

@DubboService
public class ProviderServiceImpl implements IProviderService {
    @Override
    public User getOne(Long id) {
        return new User(id);
    }
}
