package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.pojo.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author chen
 * @date 2021/4/4 18:50
 * @Description
 */
@Service
public class ConsumerService {
    /**
     * 同样的在新版本中(2.7.0开始)，该注解替换掉了旧的@Reference注解
     */
    @DubboReference
    IProviderService providerService;

    public User getOne(Long id) {
        return providerService.getOne(id);
    }
}
