package cnblogs.chenbenbuy.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author chen
 * @date 2022/7/13 22:45
 * @Description
 */
public class MyTaskListener implements TaskListener {


    /**
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        if ("第一步".equals(delegateTask.getName()) && "create".equals(delegateTask.getEventName())) {
            delegateTask.setAssignee("我是负责人");
            System.out.println("指定四大分类是快点解封了");
        }
    }
}
