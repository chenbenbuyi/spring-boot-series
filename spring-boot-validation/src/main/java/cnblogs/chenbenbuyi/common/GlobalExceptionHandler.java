package cnblogs.chenbenbuyi.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

/**
 * @RestControllerAdvice 差不多可以理解为 全局异常注解 @ControllerAdvice 和 json响应注解 @ResponseBody 的结合
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     *  通常该处理器中会全局处理很多类型的异常，但基于MVC的软件架构，通常可分为进入控制器之前和之后两类异常来看
     *  进入控制器之后，主要是业务处理和后端持久层抛出的异常，不太好总括，但进入控制器之前的异常通常却比较好概括，基本都是
     *  javax.servlet.ServletException 类型异常的子异常，常见的如：
     *  HttpRequestMethodNotSupportedException ，请求方法不支持，如以get方式请求post方法
     *  HttpMediaTypeNotSupportedException ，如控制器参数有@RequestBody 注解，但是请求头 content-type 不是 application/json 就会抛出此异常
     *  MissingServletRequestParameterException 缺少请求参数异常，比如数据实体需要的参数项缺少或完全没有参数等
     */

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public Result<String> methodNotAllowedException(HttpRequestMethodNotSupportedException e) {
        return Result.result("不支持'" + e.getMethod() + "'请求", ResultCode.FAILED);
    }

    /**
     * 请求400错误：
     * ①前端传的参数类型或者名称与后台接收参数的实体类的属性类型或者名称不一致；
     * ②前端提交ajax请求的数据应该是json格式字符串的，但是却没有将对象转换成json格式的字符串。
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleException(HttpMessageNotReadableException e) {
        log.error("请求400错误", e);
        return "请求参数错误";
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public String test(){
//        return "只是测试";
//    }

    /**
     *  If the bean validation is failed, it will trigger a MethodArgumentNotValidException.
     *  不同的异常处理拦截方法，不能指定相同的异常，否则会抛出异常，例如：
     * java.lang.IllegalStateException: Ambiguous @ExceptionHandler method mapped for [class org.springframework.web.bind.MethodArgumentNotValidException]
     *  但是，异常的继承关系，如何处理呢？也就是两个异常处理方法，一个捕获的是父类异常，一类捕获的是子类异常，那么哪个异常处理方法会捕获异常呢？
     *
     *  答案是：根据精准匹配原则，找那个关系最近的。抛出的异常有对应的异常处理器自然最好，若没有则找父类，以此往上
     *  底层源码可以参见：{@link org.springframework.web.method.annotation.ExceptionHandlerMethodResolver#getMappedMethod}
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public Result argumentValidationHandler(Exception ex) {
        String errorMsg = "参数验证错误";
        BindingResult bindingResult = null;
        if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            bindingResult = bindException.getBindingResult();
        }
        if (ex instanceof MethodArgumentNotValidException) {
            // MethodArgumentNotValidException 异常中绑定了错误返回对象
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            bindingResult = methodArgumentNotValidException.getBindingResult();
        }
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex;
            errorMsg = constraintViolationException.getMessage();
        }
        if (bindingResult != null) {
            List<String> list = new LinkedList<>();
            bindingResult.getFieldErrors().forEach(error -> {
                String field = error.getField();
                Object value = error.getRejectedValue();
                String msg = error.getDefaultMessage();
                list.add(String.format("校验错误字段: %s 错误值: %s 原因: %s", field, value, msg));
            });
            errorMsg = list.toString();
        }
        return Result.failed(errorMsg);
    }
}