package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.Result;
import cnblogs.chenbenbuyi.model.User;
import com.google.common.collect.ImmutableList;
import io.swagger.annotations.Api;
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
@Api("Api 注解")
@Controller
@RequestMapping("/swagger")
public class SwaggerTestController {
    private static ImmutableList<User> list;

    // 初始化内存数据模拟数据库数据
    static {
        list = ImmutableList.of(new User(1L), new User(2L));
    }

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation("获取列表信息")
    public Result list() {
        return Result.ok(list);
    }


    /**
     * @ApiImplicitParam 注解是为了描述这个方法之中的参数。其中的name，value属性标识参数名和参数值 多个参数用 @ApiImplicitParams
     * dataType 标识这个参数的类型，默认为String，如果是数组类型，需要加上allowMultiple=true
     * paramType是标识这个参数应该在哪去获取，对应：
     * header-->放在请求头。请求参数的获取注解：@RequestHeader
     * query -->常用于get请求的参数拼接。请求参数的获取注解：@RequestParam
     * path -->(用于restful接口)-->请求参数的获取获取注解：@PathVariable
     * body -->放在请求体。请求参数的获取注解：@RequestBody
     */
    @GetMapping("/getOne")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    public @ResponseBody
    User getOne(@RequestParam Long id) {
        return list.stream().filter(user -> Objects.equals(user.getId(), id)).findAny().orElse(null);
    }
}
