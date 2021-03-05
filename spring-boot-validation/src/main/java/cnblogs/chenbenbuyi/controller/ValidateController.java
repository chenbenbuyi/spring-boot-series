package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.GlobalExceptionHandler;
import cnblogs.chenbenbuyi.common.Result;
import cnblogs.chenbenbuyi.common.UpdateValid;
import cnblogs.chenbenbuyi.model.Person;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class ValidateController {


    /**
     * 使用 @Valid 或 @Validated 指定要校验的实体类，如果入参校验不过，错误信息会保存在类 BindingResult中.本例中使用BindingResult类型参数直接获取异常信息，而不是全局异常处理
     * 辨析：
     *
     * @Valid 是 javax 中 标准JSR-303规范的标记型注解
     * @Validated 是Spring提供的注解，是标准JSR-303的一个变种（补充），提供了一个分组功能，可以在入参验证时，根据不同的分组采用不同的验证机制。
     * 在Controller中校验方法参数时，使用@Valid和@Validated并无特殊差异（若不需要分组校验的话）
     * @Validated 注解可以用于类级别，用于支持Spring进行方法级别的参数校验。@Valid可以用在属性级别约束，用来表示级联校验。
     * @Validated 只能用在类、方法和参数上，而@Valid可用于方法、字段、构造器和参数上。
     */
    @PostMapping("/person")
    public Map<String, Object> bindingResultTest(@Validated @RequestBody Person person, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        if (result.hasErrors()) {
            List<String> res = new ArrayList<>();
            result.getFieldErrors().forEach(error -> {
                String field = error.getField();
                Object value = error.getRejectedValue();
                String msg = error.getDefaultMessage();
                res.add(String.format("错误字段 ： %s 错误值 ： %s 原因 ： %s", field, value, msg));
            });
            map.put("msg", res);
            return map;
        }
        map.put("msg", "success");
        return map;
    }

    /**
     * 在接口方法中利用BindingResult处理校验数据过程中的信息虽然可行，但在接口众多的情况下，就显得有些冗余
     * 通常的做法是定义全局异常处理器，捕捉抛出的MethodArgumentNotValidException异常并进行相应的处理。
     * {@link GlobalExceptionHandler}
     */
    @PostMapping("/person2")
    public Result globleExce(@Valid @RequestBody Person person) {
        return Result.ok();
    }


    /**
     * save 和 update 方法用来比较分组测试 ——新增方法不需要id,修改方法必须要id值
     *
     * @Validated 支持分组，@Valid 不支持分组
     */
    @PostMapping("/save")
    public Result saveGroup(@Validated @RequestBody Person person) {
        return Result.ok();
    }

    @PostMapping("/update")
    public Result updateGroup(@Validated(UpdateValid.class) @RequestBody Person person) {
        return Result.ok();
    }


    /**
     * 单字段校验
     * get 请求中，利用@Validated精确校验输入参数，而不是整个对象， 对应会抛出 ConstraintViolationException 异常
     * 要求对应的 类 上必须标注 @Validated 注解，@Valid 实测不起作用，也就是说 @Vaild 并不会校验这种直接写在控制器方法上的输入参数
     */
    @GetMapping("/person/{id}/{name}")
    public Result alidatedVali(@PathVariable("id") @Min(1) Long id, @PathVariable("name") @Size(min = 5, max = 10) String name) {
        return Result.ok();
    }

    /**
     * 参数为集合类型对象时
     * post方法传入集合对象,使用@Valid（@Validated实测不起作用，也就是说必须是类上@Validated方法集合参数前@Valid ）
     * 注意：同单字段校验一样，都要在控制层类上添加 @Validated 注解,否则不会触发进行校验
     */
    @PostMapping("/testUserList")
    public Result testUserList(@Valid @RequestBody List<Person> person) {
        return Result.ok();
    }

}