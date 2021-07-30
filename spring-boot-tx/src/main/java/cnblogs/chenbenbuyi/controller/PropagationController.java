package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.service.IPropagationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2021/7/22 11:50
 * @author: chen
 * @desc:
 */
@RequestMapping("/propagation")
@RestController
public class PropagationController {

    @Autowired
    IPropagationService propagationService;

    @GetMapping("/index")
    public String index() {
        propagationService.increase();
        return "测试而已";
    }
}
