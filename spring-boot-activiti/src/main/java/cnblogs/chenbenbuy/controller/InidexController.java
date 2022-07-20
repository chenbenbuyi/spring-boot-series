package cnblogs.chenbenbuy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Date 2022/7/9 20:48
 * @Created by csxian
 */
@RestController
@RequestMapping("/index")
public class InidexController {

    @GetMapping("/chen")
    public String index() {
        return "chenshaoxian";
    }
}
