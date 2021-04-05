package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.Result;
import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.enums.Sex;
import cnblogs.chenbenbuyi.service.IUserService;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

/**
 * @date: 2020/11/6 14:18
 * @author: chen
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public Result<UserEntity> getOne(@PathVariable Long id) {
        return Result.ok(userService.getOneById(id));
    }


    @PutMapping("/update")
    public Result<UserEntity> del(@RequestBody UserEntity user) {
        userService.updateUser(user);
        return Result.ok(user);
    }


    @GetMapping("/del/{id}")
    public Result<UserEntity> del(@PathVariable Long id) {
        userService.delById(id);
        return Result.ok();
    }


}
