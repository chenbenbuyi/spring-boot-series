package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.model.Tx;

/**
 * @date: 2021/7/22 11:43
 * @author: chen
 * @desc: 事务传播特性接口
 */
public interface IPropagationService extends IBaseService<Tx>{
    void increase();
    void decrease();
}
