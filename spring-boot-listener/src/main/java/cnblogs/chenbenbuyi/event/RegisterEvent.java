package cnblogs.chenbenbuyi.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author chen
 * @date 2021/6/13 16:13
 * @Description 定义用户注册事件
 */
public class RegisterEvent extends ApplicationEvent{

    public RegisterEvent(Object source) {
        super(source);
    }
}
