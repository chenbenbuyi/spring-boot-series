package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.mapper.TxMapper;
import cnblogs.chenbenbuyi.model.Tx;
import cnblogs.chenbenbuyi.service.IRepeatablereadService;
import org.springframework.stereotype.Service;

/**
 * @author chen
 * @date 2021/4/10 15:57
 * @Description
 */

@Service
public class RepeatablereadServiceImpl extends BaseServiceImpl<TxMapper, Tx> implements IRepeatablereadService {
}
