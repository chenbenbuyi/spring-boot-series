import cnblogs.chenbenbuy.ActivitiApplication;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.catalina.security.SecurityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

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


    @Autowired
    private TaskRuntime taskRuntime;

    // 资源管理对象（对定义好的流程进行部署等）
    @Autowired
    private RepositoryService repositoryService;

    // 历史管理对象
    @Autowired
    private HistoryService historyService;

    /**
     * 部署流程定义（即将定义的流程文件入库）
     * 注入插入以下几张表的数据：
     * act_re_deployment 流程部署表，每次部署新增记录。同一个请假记录流程，只需部署一次(但每个流程时可以多次部署的)，但是不同的人请假就会产生多条请假申请的流程定义（act_re_procdef）
     * act_re_procdef   流程定义表，生成流程定义信息
     * act_ge_bytearray  流程资源表，即流程部署定义的bpmn文件、png文件的存放的地方
     * <p>
     * act_ge_property 会发生修改
     */
    @Test
    public void testDeployment() {
        Deployment deploy = repositoryService.createDeployment()
                // 实测该名称和画流程定义中的流程名称不是同一个东西，这个名称是指部署流程时随便取名称，和定义的流程的名称不是同一个概念
                .name("随便取的名字33333")
                .addClasspathResource("processes/陈某人测试流程.bpmn")
//                .addClasspathResource("processes/陈某人测试流程.png")
                .deploy();

        System.out.println("流程部署Id:" + deploy.getId());
        System.out.println("流程部署名称:" + deploy.getName());
        System.out.println("流程部署的标识Key:" + deploy.getKey());
        System.out.println("流程部署的Version:" + deploy.getVersion());

    }

    /**
     * 通过zip的形式部署，解决多大批量流程文件部署问题
     */
    @Test
    public void testDeploymentByZip() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("processes/processes.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        Deployment deploy = repositoryService.createDeployment().addZipInputStream(zipInputStream)
                .deploy();
        System.out.println("流程部署Id:" + deploy.getId());
        System.out.println("流程部署名称:" + deploy.getName());
        System.out.println("流程部署的标识Key:" + deploy.getKey());
        System.out.println("流程部署的Version:" + deploy.getVersion());


    }


    /**
     * 流程定义查询(对act_re_procdef表的查询)
     */
    @Test
    public void testQueryProcessDefinition() {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.processDefinitionKey("id_2").orderByProcessDefinitionVersion().desc().list();

        for (ProcessDefinition processDefinition : processDefinitions) {
            System.out.println("流程定义id:" + processDefinition.getId());
            System.out.println("流程定义名称:" + processDefinition.getName());
            System.out.println("流程定义版本:" + processDefinition.getVersion());
            System.out.println("流程定义key:" + processDefinition.getKey());
        }

    }

    /**
     * 开启流程实例（流程定义的执行实例）：
     *      流程部署在activiti的库以后就可以通过工作流管理业务流程，比如完成请假申请流程。类比Java中的类和对象实例的关系，类定义(部署好定义的流程)好之后，就可以创建对象使用了
     *
     * <p>
     * act_hi_actinst  流程实例执行历史
     * act_hi_identitylink  流程的参与用户信息历史
     * act_hi_procinst   流程实例历史信息
     * act_hi_taskinst   流程任务历史
     * act_ru_execution  流程正在执行信息
     * act_ru_identitylink  流程参与用户信息
     * act_ru_task   任务信息
     */
    @Test
    public void testStartProcess() {
        // 这里的key 为流程的ID
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("id_2");
        System.out.println("流程定义Id:" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例ID:" + processInstance.getProcessInstanceId());
        System.out.println("流程活动ID:" + processInstance.getActivityId());
    }


    /**
     * 个人任务查询
     */
    @Test
    public void testFindTask() {
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

        // 也可以用下面的方式分页查询
        Page<org.activiti.api.task.model.Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));

        if(tasks.getTotalItems()>0){
            for (org.activiti.api.task.model.Task task : tasks.getContent()) {
                System.out.println(task.getAssignee());
            }
        }

    }


    /**
     * 历史信息查询
     */
    @Test
    public void testHistory() {
        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
        HistoricActivityInstanceQuery hai = instanceQuery.processDefinitionId("id_3:1:7f8582fa-02ac-11ed-86e5-c82158315b7d");
        List<HistoricActivityInstance> list = hai.list();
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println("流程实例ID:"+historicActivityInstance.getProcessInstanceId());
            System.out.println("流程定义ID:"+historicActivityInstance.getProcessDefinitionId());
        }

    }

    /**
     * 删除流程部署信息
     */
    @Test
    public void testDeleteDeployment() {
        String deploymentId = repositoryService.getProcessDefinition("id_2:3:458e9f8c-01fa-11ed-a0b9-c82158315b7d").getDeploymentId();
        // 设置true 强制删除 以此来删除一些半途没有完成的流程
        repositoryService.deleteDeployment(deploymentId, true);
    }
}
