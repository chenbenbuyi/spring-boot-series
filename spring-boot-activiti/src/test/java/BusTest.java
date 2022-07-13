import cnblogs.chenbenbuy.ActivitiApplication;
import cnblogs.chenbenbuy.listener.MyTaskListener;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @date 2022/7/13 22:07
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
public class BusTest {

    // 流程运行管理对象
    @Autowired
    private RuntimeService runtimeService;

    // 任务管理对象
    @Autowired
    private TaskService taskService;

    // 资源管理对象（对定义好的流程进行部署、流程定义的全部挂起、激活等）
    @Autowired
    private RepositoryService repositoryService;

    // 历史管理对象
    @Autowired
    private HistoryService historyService;


    /**
     * 启动流程实例，则会在act_ru_task中插入一条记录，当流程任务走完，该表中对应任务的记录会被删除
     * act_ru_task 流程正在执行信息
     */
    @Test
    public void testAddBusKey() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("id_4", "5000");
        System.out.println(processInstance.getBusinessKey());
    }

    /**
     * 动态设置责任人-通过UEL表达式
     */
    @Test
    public void testAssignee() {
        Map<String, Object> map = new HashMap<>();
        map.put("user1", "第一个负责人");
        map.put("user2", "第二个负责人");
//        runtimeService.startProcessInstanceByKey("id_4", map);
        // 动态设置责任人-通过监听器方式
        runtimeService.startProcessInstanceByKey("id_5");
    }


    /**
     *
     */
    @Test
    public void testAssigneeByListener() {
    }
}
