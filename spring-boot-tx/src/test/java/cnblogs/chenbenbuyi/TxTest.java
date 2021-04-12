package cnblogs.chenbenbuyi;

import cnblogs.chenbenbuyi.model.Tx;
import cnblogs.chenbenbuyi.service.IDefaultService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author chen
 * @date 2021/4/10 15:36
 * @Description
 */
@SpringBootTest
public class TxTest {

    @Autowired
    IDefaultService defaultService;

    @Test
    public void defaultTest(){
        defaultService.transaction1();
    }

    @Test
    public void defaultTest2(){
        defaultService.transaction2();
    }

    @Test
    public void defaultTest3(){
        defaultService.transaction3();
    }
}
