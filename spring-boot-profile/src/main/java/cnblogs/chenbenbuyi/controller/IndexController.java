package cnblogs.chenbenbuyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2021/2/26 11:21
 * @author: chen
 * @desc:
 */

@Controller
@RestController("/index")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "chenbenbuyi";
    }
}
