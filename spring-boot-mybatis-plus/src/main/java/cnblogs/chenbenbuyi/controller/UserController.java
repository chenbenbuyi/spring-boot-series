package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.Result;
import cnblogs.chenbenbuyi.entity.UserEntity;
import cnblogs.chenbenbuyi.enums.Sex;
import cnblogs.chenbenbuyi.service.IUserService;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
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
     *   Spring 接受参数时默认就支持枚举字面量参数传入，如果是简单的枚举没做任何处理，响应数据也是枚举字面量，如：
     *   {
     *     "code": 0,
     *     "msg": "成功",
     *     "data": "FEMALE"
     * }
     *  ps:为什么Spring MVC参数接受默认就支持字面量？
     *  无论是 GET 请求还是 POST 请求表单中的参数字符串，其中的枚举的转化都是通过 {@link org.springframework.core.convert.support.StringToEnumConverterFactory} 来实现的.
     *   该类实现了接口 ConverterFactory ，通过调用 Enum.valueOf(Class, String) 实现了这个功能。
     *  向下追溯源码可以发现该方法实际上是从一个 Map<String, Enum> 的字典中获取了转换后的实际值，这个 String 类型的 Key 的获取方式就是 Enum.name() 返回的结果，即枚举的字面值。
     *
     *
     *  页面传值：枚举字面量——FEMALE，响应——
     *  {
     *     "code": 0,
     *     "msg": "成功",
     *     "data": "女"
     * }
     *
     * jackson 的 @JsonValue 注解可以控制枚举字段响应在页面的字段值，以{@link Sex}为例，比如标注在desc上，页面响应就是男或女，在 code 字段上，就是 0 或 1
     *
     * 页面传值：0 或 1 ，参数不匹配，无法转换  IllegalArgumentException
     * Failed to convert value of type 'java.lang.String' to required type 'cnblogs.chenbenbuyi.enums.Sex'
     * Failed to convert from type [java.lang.String] to type [cnblogs.chenbenbuyi.enums.Sex] for value '1'
     */
    @PostMapping("/enum")
    @ResponseBody
    public Result<Sex> saveEnum(Sex sex) {
        return Result.ok(sex);
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

    /**
     *  测试枚举值的保存和页面数据响应
     *  Spring使用 @RequeseBody 接收枚举参数时支持  ordinal，只不过通常不建议使用 ordinal，因为排序字段ordinal通常和业务关联性不强
     *  网上说还支持字面量，但自己实测中如果枚举字段添加了 @JsonValue ，则会影响字面量的传值方式，具体可以跟踪源码 {@link EnumDeserializer}
     *
     */
    @PostMapping("/save2")
    @ResponseBody
    public Result<UserEntity> save2(@RequestBody UserEntity user) {
        userService.saveOrUpdate(user);
        return Result.ok(user);
    }

    @PostMapping("/en/{sex}")
    @ResponseBody
    public Result<Sex> save2(@PathVariable Sex sex) {
        return Result.ok(sex);
    }
}
