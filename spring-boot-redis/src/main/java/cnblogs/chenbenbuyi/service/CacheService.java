package cnblogs.chenbenbuyi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @date: 2021/4/1 11:26
 * @author: chen
 * @desc:
 */

@Service
@Slf4j
public class CacheService {


    @Cacheable(cacheNames = "test",key = "'test'")
    public String getNameformDb(){
        log.info("从数据库查询。。。。。。。。。。。。。。。。。。。");
        return "name";
    }
}
