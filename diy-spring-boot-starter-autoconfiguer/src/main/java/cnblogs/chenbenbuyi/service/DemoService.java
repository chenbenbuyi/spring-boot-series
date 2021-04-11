package cnblogs.chenbenbuyi.service;

import cnblogs.chenbenbuyi.config.DemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chen
 * @date 2021/4/11 18:53
 * @Description
 */

@Service
public class DemoService {

    @Autowired
    DemoProperties properties;

    public void print(){
        System.out.println(properties);
    }
}
