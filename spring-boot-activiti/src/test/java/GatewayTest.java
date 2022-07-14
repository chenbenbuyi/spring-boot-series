import cnblogs.chenbenbuy.ActivitiApplication;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen 网关相关测试
 * @date 2022/7/14 21:47
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
public class GatewayTest {

    // 任务管理对象
    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskRuntime taskRuntime;

    // 资源管理对象（对定义好的流程进行部署等）
    @Autowired
    private RepositoryService repositoryService;

    // 历史管理对象
    @Autowired
    private HistoryService historyService;

    @Test
    public void testDeployment() {
        Deployment deploy = repositoryService.createDeployment()
                .name("排他网关测试")
                .addClasspathResource("processes/排他网关.bpmn")
                .deploy();

        System.out.println("流程部署Id:" + deploy.getId());
        System.out.println("流程部署名称:" + deploy.getName());
    }

    /**
     *
     */
    @Test
    public void testStartProcess() {
        // 这里的key 为流程的ID 58bcee84-037f-11ed-93b8-c82158315b7d
        Map<String, Object> map = new HashMap<>();
        map.put("status", "1");
//        启动实例时设置变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("fenzhi", map);
        System.out.println("流程定义Id:" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例ID:" + processInstance.getProcessInstanceId());
        // 通过流程实例设置变量
//        runtimeService.setVariable("流程实例Id",map );

    }


    @Test
    public void testComplete() {
        TaskQuery taskQuery = taskService.createTaskQuery();
//        流程定义Id:fenzhi:2:9ba5ebc8-0381-11ed-91a3-c82158315b7d
//        流程实例ID:b9d6cd40-0381-11ed-8f7c-c82158315b7d
        List<Task> lists = taskQuery.processDefinitionKey("fenzhi").list();
        for (Task task : lists) {
            System.out.println("流程实例ID:" + task.getProcessInstanceId());
            System.out.println("任务ID:" + task.getId());
            System.out.println("任务名称:" + task.getName());
            System.out.println("负责人:" + task.getAssignee());

            // 此处即为任务办理时设置流程变量，控制分支执行
            Map<String, Object> map = new HashMap<>();
            map.put("status", "1");

//            taskService.complete(task.getId()); // 完成任务
            taskService.complete(task.getId(), map); // 完成任务
            // 通过任务设置流程变量
//            taskService.setVariables(task.getId(), map );

        }




    }


}
