import cnblogs.chenbenbuy.ActivitiApplication;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chen 网关相关测试
 * @date 2022/7/14 21:47
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
public class GatewayTest2 {

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

    /**
     * 流程部署Id:1
     * 流程部署名称:排他网关测试
     * 流程定义的key：paitawangguan
     * 流程定义的id：paitawangguan:1:3
     */
    @Test
    public void testDeployment() {
        Deployment deploy = repositoryService.createDeployment()
                .name("水电费水电费")
                .addClasspathResource("processes/随便测试.bpmn")
                .deploy();

        System.out.println("流程部署Id:" + deploy.getId());
        System.out.println("流程部署名称:" + deploy.getName());
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程定义的key：" + processDefinition.getKey());
            System.out.println("流程定义的processDefinitionId：" + processDefinition.getId());
        }
    }

    /**
     *  开启流程实例，并根据当前流程实例对象设置实例级别的流程变量
     */
    @Test
    public void testStartProcess() {
        // 审批单的表id
        String businessKey = "process_approval_id:123";
        String processDefinitionId = "myProcess_1:1:5003";
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId,businessKey);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        task.setAssignee("陈一步");
        System.out.println("流程定义Id:" + processInstance.getProcessDefinitionId());
        System.out.println("开启的流程实例ID:" + processInstance.getProcessInstanceId());
        taskService.complete(task.getId());

    }


    /**
     * 判断流程是否结束
     * 通过流程实例ID进行查询
     */
    @Test
    public void checkProcessOver() {
        String processInstanceId = "2501";
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (Objects.isNull(processInstance)) {
            System.out.println("流程实例ID:" + processInstanceId + " 的流程已经结束");
        } else {
            System.out.println("流程实例ID:" + processInstanceId + " 流程尚未结束哦。。。。。。");
        }

    }


    /**
     * 获取当前待执行的任务
     */
    @Test
    public void findCurrentTask() {
        String processInstanceId = "2501";
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).processInstanceId(processInstanceId).singleResult();
        System.out.println("当前待审批的任务ID:" + task.getId());
        System.out.println("当前待审批的任务BusinessKey:" + task.getBusinessKey());
        System.out.println("当前待审批的任务名称:" + task.getName());
        System.out.println("当前待审批的任务Assignee:" + task.getAssignee());

    }

    /**
     * 查询指定用户已处理完成的任务
     */
    @Test
    public void findCompleteTask() {
//        String userId = "陈一步";
        String userId = "technology_expert";
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).orderByTaskCreateTime().desc().finished().list();
        historicTaskInstances.forEach(h -> {
            System.out.println("任务id：" + h.getId());
            System.out.println("任务名称：" + h.getName());
            System.out.println("任务的办理人：" + h.getAssignee());
            System.out.println("任务的开始时间：" + h.getStartTime());
            System.out.println("任务的结束时间" + h.getEndTime());
            System.out.println("流程实例ID：" + h.getProcessInstanceId());
            System.out.println("流程定义ID：" + h.getProcessDefinitionId());
            System.out.println("业务唯一标识BusinessKey：" + h.getBusinessKey());
            System.out.println("===========================================");
        });
    }


    @Test
    public void testComplete() {
        String processDefinitionKey = "paitawangguan";
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> lists = taskQuery.processDefinitionKey(processDefinitionKey).list();
        for (Task task : lists) {
            System.out.println("流程实例ID:" + task.getProcessInstanceId());
            System.out.println("任务ID:" + task.getId());
            System.out.println("任务名称:" + task.getName());
            System.out.println("负责人:" + task.getAssignee());
            Map<String, Object> variablesMap = new HashMap<>(2);
            variablesMap.put("days", 2);
//            taskService.complete(task.getId(), variablesMap); // 完成任务
        }
    }


    @Test
    public void testCompleteByTaskId() {
        String taskId = "10005";
        String userId = "陈二步";
        Map<String, Object> variablesMap = new HashMap<>(2);
        variablesMap.put("pass", true);
        taskService.setAssignee(taskId, userId);
        taskService.complete(taskId, variablesMap); // 完成任务
    }

}
