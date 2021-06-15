package cnblogs.chenbenbuyi.listener;

import cnblogs.chenbenbuyi.event.RegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author chen
 * @date 2021/6/13 16:17
 * @Description
 */
@Component
public class MyListener implements ApplicationListener<RegisterEvent> {
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        System.out.println("监听事件："+event.getClass());
    }
}
