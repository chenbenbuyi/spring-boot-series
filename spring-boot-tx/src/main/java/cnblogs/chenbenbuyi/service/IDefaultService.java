package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.model.Tx;
import org.springframework.transaction.TransactionDefinition;

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
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public interface IDefaultService extends IBaseService<Tx> {
    void transaction1();

    void transaction2();

    void transaction3();
}
