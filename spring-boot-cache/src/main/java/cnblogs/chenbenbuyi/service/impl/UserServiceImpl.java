package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.config.MyCacheConfig;
import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.mapper.UserMapper;
import cnblogs.chenbenbuyi.service.IUserService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @date: 2020/11/3 15:35
 * @author: chen
 * {@link CacheConfig}  在类上提取一些公共属性配置
 */
@Service
//@CacheConfig(cacheNames = "user",keyGenerator = "myKeyGenerator")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity> implements IUserService {


    /**
     * @Cacheable 将方法的运行结果进行缓存，下次查询先相同的数据，直接从缓存获取，不会再调用标注方法
     * 属性值介绍：
     * cacheNames 指定缓存名字，相当于分类，分组,cacheNames 可以指定多个缓存名字
     * key: 缓存数据的 key 值，默认使用方法参数值作为缓存key,可使用 SpEL表达式进行设置,如 #id ，等同于 #a0,#root.args[0] 等
     * keyGenerator： key的生成器，默认使用的是SimpleKeyGenerator ，即以参数的值作为key,如果自定义有key生成器可用此属性指定。key和keyGenerator 二选一即可
     * cacheManager：缓存管理器是与缓存组件类型相关联，适用于同时配置多种缓存组件的情况，由于基于抽象编程，Spring 通过缓存管理器屏蔽了底层具体缓存组件的差异。
     * 实际上，Spring 为每一种缓存组件都提供了默认的缓存管理器实现，如RedisCacheManager， EhCacheCacheManager，ConcurrentMapCacheManager等
     * cacheResolver ：缓存解析器是用来管理缓存管理器的，按照官方文档，cacheManager 和 cacheResolver 是互斥参数，类似于 KeyGenerator 跟 key
     * condition：指定什么条件下才进行缓存 如 "#id>1"
     * unless: 否定缓存，unless 指定的条件为 true则不缓存，可以获取结果进行判断 ，如 unless = "#result==null"，表示本来是要缓存结果的，在返回值为空的情况下，就不缓存
     * 关于 condition和 unless有如下测试验证的结论：
     * 　condition 不指定相当于 true，unless 不指定相当于 false
     * 　　　     当 condition = false，一定不会缓存；
     * 　　　　   当 condition = true，且 unless = true，不缓存；
     * 　　　     当 condition = true，且 unless = false，缓存；
     * sync：是否同步，默认为false ，即异步缓存。在一个多线程的环境中，某些操作可能被相同的参数并发地调用，这样同一个 value 值可能被多次计算（或多次访问 db），这样就达不到缓存的目的。
     * 针对这些可能高并发的操作，我们可以使用 sync 参数来告诉底层的缓存提供者将缓存的入口锁住，这样就只能有一个线程计算操作的结果值，而其它线程需要等待，这样就避免了 n-1 次数据库访问。
     * 　         sync = true 可以有效的避免缓存击穿的问题。ps:  缓存穿透是指缓存和数据库中都没有的数据, 缓存击穿是指缓存中没有但数据库中有的数据
     */

//    @Cacheable(cacheNames = "user", key = "#id",condition = "#id>1")
//    @Cacheable(cacheNames = "user", key = "#id", condition = "#a0>1")
//    @Cacheable(cacheNames = "user", key = "#id",unless = "#result==null")
    @Cacheable(cacheNames = "user", keyGenerator = "myKeyGenerator", unless = "#result==null")
    @Override
    public UserEntity getOneById(Serializable id) {
        return this.getById(id);
    }


    /**
     *  @CachePut 先调用目标方法，再更新缓存值（当然，是否真的会缓存还跟一些注解参数有关，比如：unless 参数），不会影响目标方法执行。 @Cacheable 适用于查询数据的方法，@CachePut 适用于更新数据的方法，两者属性也基本相同
     *  示例中 key = "#result.id" 和 key = "#user.id"是等价的，因为是先查询方法返回结果再更新缓存
     *  注意：当前 自定义的 KeyGenerator {@link MyCacheConfig#keyGenerator()} 由于两个方法的存入键不同，所以无法完成对统一用户缓存更新的功能，自定义仅为测试
     */

//    @CachePut(cacheNames = "user",key = "#user.id")
//    @CachePut(cacheNames = "user",key = "#result.id")
    @CachePut(cacheNames = "user", keyGenerator = "myKeyGenerator")
    @Override
    public UserEntity updateUser(UserEntity user) {
        this.updateById(user);
        return user;
    }

    /**
     * @CacheEvict 删除缓存，除了和上面两个注解基本相同的属性外，还有以下属性：
     *  allEntries：清楚指定缓存中的所有数据
     *  beforeInvocation：在方法执行之前还是之后删除缓存，默认在方法执行之后执行
     *      此种情况下，如果方法执行过程中发生错误，则不会删除缓存
     */

    @CacheEvict(cacheNames = "user", key = "#id")
    @Override
    public boolean delById(Serializable id) {
        return this.removeById(id);
    }

    /**
     *  {@link Caching} 几个缓存注解的组合注解，可以综合利用各个注解的特性
     */

}
