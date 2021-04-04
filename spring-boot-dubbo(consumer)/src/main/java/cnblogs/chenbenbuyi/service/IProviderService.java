package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.pojo.User;


/**
 * @author chen
 * @date 2021/4/4 17:29
 * @Description
 * 该接口定义的目的仅仅是为了 消费端根据全路径名引用，如果服务提供方和消费方暴露或订阅的接口再第三方模块两者都能引用到，则不存在此问题
 */
public interface IProviderService {

    User getOne(Long id);
}
