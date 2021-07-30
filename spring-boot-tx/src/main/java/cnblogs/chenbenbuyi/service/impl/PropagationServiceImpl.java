package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.mapper.TxMapper;
import cnblogs.chenbenbuyi.model.Tx;
import cnblogs.chenbenbuyi.service.IPropagationService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chen
 * @date 2021/4/10 15:56
 *  Spring 事务传播特性
 *  1 死活不要事务
 *  {@link TransactionDefinition#PROPAGATION_NEVER} 没有就非事务方式执行，有就抛异常
 *  {@link TransactionDefinition#PROPAGATION_NOT_SUPPORTED} 没有就非事务方式执行，有就温柔些的挂起事务然后以非事务方式执行
 *
 *  2 必须有事务的
 *  {@link TransactionDefinition#PROPAGATION_REQUIRES_NEW} 总是会新建事务，如果原来有事务，则挂起原事务
 *  {@link TransactionDefinition#PROPAGATION_NESTED} 没有就新建，有就嵌套
 *  {@link TransactionDefinition#PROPAGATION_REQUIRED} 没有就新建，有就加入  这是默认值
 *  {@link TransactionDefinition#PROPAGATION_MANDATORY} 没有抛异常，有就使用当前事务
 *
 *  3 可有可无
 *  {@link TransactionDefinition#PROPAGATION_SUPPORTS} 有就有，没有就算了
 *
 *  测试要注意内部方法调用时事务的失效影响
 *
 */
@Slf4j
@Service
public class PropagationServiceImpl extends BaseServiceImpl<TxMapper, Tx> implements IPropagationService {

    @Autowired
    IPropagationService propagationService;


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void increase() {
        UpdateWrapper<Tx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1);
        Tx tx = this.getById(1);
        tx.setMoney(6000);
        this.update(tx, updateWrapper);
        log.info("事务1 修改数据库的值：" + tx);
        propagationService.decrease();
    }



//    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void decrease() {
        UpdateWrapper<Tx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 2);
        Tx tx = this.getById(2);
        tx.setMoney(2000);
        this.update(tx, updateWrapper);
        log.info("事务2 修改数据库的值：" + tx);
        int i = 1/0;
    }
}
