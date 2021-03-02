package cnblogs.chenbenbuyi.controller;

import cnblogs.chenbenbuyi.common.GlobalExceptionHandler;
import cnblogs.chenbenbuyi.common.Result;
import cnblogs.chenbenbuyi.model.Person;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ValidateController {


    /**
     * 使用 @Valid 或 @Validated 指定要校验的实体类，如果入参校验不过，错误信息会保存在类 BindingResult中.
     * 辨析：
     *
     * @Valid 是 javax 中 标准JSR-303规范的标记型注解
     * @Validated 是Spring提供的注解，是标准JSR-303的一个变种（补充），提供了一个分组功能，可以在入参验证时，根据不同的分组采用不同的验证机制。
     * 在Controller中校验方法参数时，使用@Valid和@Validated并无特殊差异（若不需要分组校验的话）
     */
    @PostMapping("/person")
    public Map<String, Object> validatePerson(@Validated @RequestBody Person person, BindingResult result) {
        Map<String, Object> map = new HashMap<>();
        if (result.hasErrors()) {
            List<String> res = new ArrayList<>();
            result.getFieldErrors().forEach(error -> {
                String field = error.getField();
                Object value = error.getRejectedValue();
                String msg = error.getDefaultMessage();
                res.add(String.format("错误字段 -> %s 错误值 -> %s 原因 -> %s", field, value, msg));
            });
            map.put("msg", res);
            return map;
        }
        map.put("msg", "success");
        return map;
    }

    /**
     *
     * 在接口方法中利用BindingResult处理校验数据过程中的信息虽然可行，但在接口众多的情况下，就显得有些冗余
     * 通常的做法是定义全局异常处理器，捕捉抛出的MethodArgumentNotValidException异常并进行相应的处理。
     * {@link GlobalExceptionHandler}
     */
    @PostMapping("/person2")
    public Result<String> validatePerson2(@Valid @RequestBody Person person) {
        System.out.println(person);
        return Result.ok();
    }
}