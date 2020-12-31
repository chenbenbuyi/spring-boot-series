package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.service.IBaseService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @date: 2020/10/20 17:02
 * @author: chen
 * @desc: 自定义基础实现，便于扩展公共方法
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {
}
