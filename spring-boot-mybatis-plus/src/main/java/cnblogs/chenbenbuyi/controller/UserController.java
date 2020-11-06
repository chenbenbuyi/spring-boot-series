package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.Result;
import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @date: 2020/11/6 14:18
 * @author: chen
 * @desc:
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/list")
    @ResponseBody
    public Result<List<UserEntity>> index() {
        List<UserEntity> list = userService.list();
        return Result.ok(list);
    }

    /**
     *  测试枚举值的保存和页面数据响应
     */
    @PostMapping("/save")
    @ResponseBody
    public Result<UserEntity> save(UserEntity user) {
        userService.saveOrUpdate(user);
        return Result.ok(user);
    }

}
