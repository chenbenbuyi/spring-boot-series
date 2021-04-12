package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.mapper.TxMapper;
import cnblogs.chenbenbuyi.model.Tx;
import cnblogs.chenbenbuyi.service.IDefaultService;
import cnblogs.chenbenbuyi.util.SleepUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chen
 * @date 2021/4/10 15:57
 * @Description Spring 默认的事务隔离级别——跟随数据库的事务隔离级别
 */

@Slf4j
@Service
public class DefaultServiceImpl extends BaseServiceImpl<TxMapper, Tx> implements IDefaultService {

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void transaction1() {
        UpdateWrapper<Tx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1);
        Tx tx = this.getById(1);
        SleepUtil.sleep(3);
        tx.setMoney(1000);
        log.info("事务1 修改数据库的值：" + tx);
        this.update(tx, updateWrapper);
    }


    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    @Override
    public void transaction2() {
        UpdateWrapper<Tx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1);
        Tx tx = this.getById(1);
        SleepUtil.sleep(6);
        tx.setMoney(2000);
        log.info("事务2 修改数据库的值：" + tx);
        this.update(tx, updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    @Override
    public void transaction3() {
        Tx tx = this.getById(1);
        log.info("事务3第一次 数据库查询的值：" + tx);
        SleepUtil.sleep(30);
        Tx tx2 = this.getById(1);
        log.info("事务3第二次 数据库查询的值：" + tx2);

    }
}
