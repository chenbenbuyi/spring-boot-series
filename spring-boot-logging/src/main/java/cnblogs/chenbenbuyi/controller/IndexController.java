package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.service.ILoggingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2021/2/24 9:29
 * @author: chen
 * @desc:
 */

@Slf4j
@RestController
@RequestMapping("/logging")
public class IndexController {

    @Autowired
    ILoggingService loggingService;

    @GetMapping({"/", ""})
    public String index() {
        log.debug("IndexController debug 级别日志");
        log.info("IndexController info 级别日志");
        log.warn("IndexController warn 级别日志");
        log.error("IndexController error 级别日志");
        loggingService.test();
        return "logging";
    }
}
