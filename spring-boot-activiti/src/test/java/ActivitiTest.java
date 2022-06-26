import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chen
 * @date 2022/6/26 22:59
 * @Description
 */

@SpringBootTest
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
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("chenxiaoyuan");
        System.out.println("流程定义Id:"+processInstance.getProcessDefinitionId());
    }

}
