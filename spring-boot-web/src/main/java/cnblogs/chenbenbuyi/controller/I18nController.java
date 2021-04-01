package cnblogs.chenbenbuyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chen
 * @date 2021/3/30 22:42
 * @Description
 */

@Controller
@RequestMapping("/language")
public class I18nController {

    @GetMapping
    public String i18n(){
        return "language";
    }
}
