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

    // 流程运行管理对象
    @Autowired
    private RuntimeService runtimeService;

    // 任务管理对象
    @Autowired
    private TaskService taskService;

    // 资源管理对象（对定义好的流程进行部署等）
    @Autowired
    private RepositoryService repositoryService;

    // 历史管理对象
    @Autowired
    private HistoryService historyService;

    /**
     *  部署流程定义（即将定义的流程文件入库）
     *  注入插入以下几张表的数据：
     *  act_re_deployment 流程部署表，每次部署新增记录。同一个请假记录流程，只需部署一次(但每个流程时可以多次部署的)，但是不同的人请假就会产生多条请假申请的流程定义（act_re_procdef）
     *  act_re_procdef   流程定义表，生成流程定义信息
     *  act_ge_bytearray  流程资源表，即流程部署定义的bpmn文件、png文件的存放的地方
     *
     *  act_ge_property 会发生修改
     */
    @Test
    public void testDeploement() {
        Deployment deploy = repositoryService.createDeployment()
                // 实测该名称和画流程定义中的流程名称不是同一个东西，这个名称是指部署流程时随便取名称，和定义的流程的名称不是同一个概念
                .name("随便取的名字")
                .addClasspathResource("processes/陈某人测试流程.bpmn")
                .addClasspathResource("processes/陈某人测试流程.png")
                .deploy();

        System.out.println("流程部署Id:" + deploy.getId());
        System.out.println("流程部署名称:" + deploy.getName());
        System.out.println("流程部署的标识Key:" + deploy.getKey());
        System.out.println("流程部署的Version:" + deploy.getVersion());


    }

    /**
     *  开启流程实例
     *
     *  act_hi_actinst  流程实例执行历史
     *  act_hi_identitylink  流程的参与用户信息历史
     *  act_hi_procinst   流程实例历史信息
     *  act_hi_taskinst   流程任务历史
     *  act_ru_execution  流程正在执行信息
     *  act_ru_identitylink  流程参与用户信息
     *  act_ru_task   任务信息
     *
     */
    @Test
    public void testStartProcess(){
        // 这里的key 为流程的ID
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("id_1");
        System.out.println("流程定义Id:" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例ID:" + processInstance.getProcessInstanceId());
        System.out.println("流程活动ID:" + processInstance.getActivityId());
    }


    /**
     *  个人任务查询
     */
    @Test
    public void testFindTask(){
        // 这里的key 为流程的ID
        TaskQuery taskQuery = taskService.createTaskQuery();
        // 根据流程标识key和负责人进行查询
        List<Task> lists = taskQuery.processDefinitionKey("id_1").taskAssignee("chenxiaoyuan").list();
        for (Task task : lists) {
            System.out.println("流程实例ID:" + task.getProcessInstanceId());
            System.out.println("任务ID:" + task.getId());
            System.out.println("任务名称:" + task.getName());
            System.out.println("负责人:" + task.getAssignee());
            System.out.println("BusinessKey:" + task.getBusinessKey());
            System.out.println("ParentTaskId:" + task.getParentTaskId());
//            taskService.complete(task.getId()); // 完成任务
        }
    }




    @Test
    public void testDel() {
        String deploymentId = repositoryService.getProcessDefinition("my_chen:1:92170665-012a-11ed-9164-c82158315b7d").getDeploymentId();
        // 设置true 强制删除
        repositoryService.deleteDeployment(deploymentId, true);
    }
}
