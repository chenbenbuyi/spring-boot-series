package cnblogs.chenbenbuyi.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 用户事件源
 * 注意，在SpringBoot中，是不必要继承ApplicationEvent，单个的实体对象是可以直接作为事件源的
 */
@Getter
public class UserEventSource extends ApplicationEvent {

    private UserVo userVo;

    public UserEventSource(UserVo userVo) {
        super(userVo);
        this.userVo = userVo;
    }
}