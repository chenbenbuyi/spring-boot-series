package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.Result;
import cnblogs.chenbenbuyi.model.User;
import com.google.common.collect.ImmutableList;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @date: 2020/10/28 17:19
 * @author: chen
 */
@Controller
@RequestMapping("/swagger2")
public class SwaggerTestController2 {
    private static ImmutableList<User> list;

    static {
        list = ImmutableList.of(new User(3L), new User(4L));
    }

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation("获取列表信息")
    public Result list() {
        return Result.ok(list);
    }


    @GetMapping("/getOne")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    public @ResponseBody
    User getOne(@RequestParam Long id) {
        return list.stream().filter(user -> Objects.equals(user.getId(), id)).findAny().orElse(null);
    }
}
