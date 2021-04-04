package cnblogs.chenbenbuyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chen
 * @date 2021/3/30 22:42
 * @Description
 */

@Controller
@RequestMapping("/language")
public class I18nController {

    @GetMapping
    public String i18n() {
        return "language";
    }

    @GetMapping("/500")
    public String error() {
        int i = 1 / 0;
        return "language";
    }
}
