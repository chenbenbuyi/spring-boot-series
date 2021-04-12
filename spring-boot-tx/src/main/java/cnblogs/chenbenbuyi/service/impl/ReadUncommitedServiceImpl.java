package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.mapper.TxMapper;
import cnblogs.chenbenbuyi.model.Tx;
import cnblogs.chenbenbuyi.service.IReadUncommitedService;
import org.springframework.stereotype.Service;

/**
 * @author chen
 * @date 2021/4/10 15:57
 * @Description 读未提交事务隔离级别
 */

@Service
public class ReadUncommitedServiceImpl extends BaseServiceImpl<TxMapper, Tx> implements IReadUncommitedService {
}
