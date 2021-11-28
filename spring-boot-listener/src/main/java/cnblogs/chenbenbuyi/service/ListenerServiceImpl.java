package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.event.UserEventSource;
import cnblogs.chenbenbuyi.event.UserVo;
import cnblogs.chenbenbuyi.listener.EventListenerByExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author chen
 * @date 2021/11/28 13:06
 * @Description 监听器测试
 */

@Service
public class ListenerServiceImpl {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publishTest1(UserVo userVo) {
        UserEventSource userEventSource = new UserEventSource(userVo);
        applicationEventPublisher.publishEvent(userEventSource);
    }

    public void publishTest2(UserVo userVo) {
        applicationEventPublisher.publishEvent(userVo);
    }

}
