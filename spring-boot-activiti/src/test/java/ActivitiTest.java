import cnblogs.chenbenbuy.ActivitiApplication;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author chen
 * @date 2022/6/26 22:59
 * @Description
 */

@RunWith(SpringRunner.class)
/**
 * 在测试类包名和启动类包名不一致的情况下， 会出现 Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...)错误
 * 解决方式：1、包名修改为一致 2 @SpringBootTest注解上添加classes属性
 *
 */
@SpringBootTest(classes = ActivitiApplication.class)
public class ActivitiTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;


    @Test
    public void testStartPocess(){
        // 这里的key 为流程的ID
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my_chen");
        System.out.println("流程定义Id:"+processInstance.getProcessDefinitionId());
        System.out.println("流程实例ID:"+processInstance.getProcessInstanceId());
        System.out.println("流程活动ID:"+processInstance.getActivityId());
    }


    /**
     *  提交申请
     */
    @Test
    public void test2(){
        // 这里的key 为流程的ID
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> lists = taskQuery.processDefinitionKey("my_chen").list();
        for (Task task : lists) {
            System.out.println("流程实例ID:"+task.getProcessInstanceId());
            System.out.println("任务ID:"+task.getId());
            System.out.println("任务名称:"+task.getName());
            System.out.println("负责人:"+task.getAssignee());
            taskService.complete(task.getId()); // 完成任务
        }
    }

    @Test
    public void test3(){
        // 这里的key 为流程的ID
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionKey("my_chen").list();
        for (ProcessInstance processInstance : list) {
            System.out.println("流程实例ID:"+processInstance.getProcessInstanceId());
            System.out.println("所属流程定义ID:"+processInstance.getProcessDefinitionId());
            System.out.println("是否执行完成:"+processInstance.isEnded());
            System.out.println("是否暂停:"+processInstance.isSuspended());
            System.out.println("当前活动ID:"+processInstance.getActivityId());
            System.out.println("业务关键字:"+processInstance.getBusinessKey());
        }
        System.out.println(list.size());
    }



    @Test
    public void testDel(){
        String deploymentId = repositoryService.getProcessDefinition("my_chen:1:92170665-012a-11ed-9164-c82158315b7d").getDeploymentId();
        // 设置true 强制删除
        repositoryService.deleteDeployment(deploymentId,true);
    }
}
