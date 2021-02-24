package cnblogs.chenbenbuyi.service.impl;

import cnblogs.chenbenbuyi.service.ILoggingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date: 2021/2/24 9:32
 * @author: chen
 * @desc:
 */

@Slf4j
@Service
public class LoggingServiceImpl implements ILoggingService {
    @Override
    public void test() {
        log.debug("ILoggingService debug 级别日志");
        log.info("ILoggingService info 级别日志");
        log.warn("ILoggingService warn 级别日志");
        log.error("ILoggingService error 级别日志");
    }
}
