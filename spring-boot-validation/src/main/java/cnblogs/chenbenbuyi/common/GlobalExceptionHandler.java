package cnblogs.chenbenbuyi.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 请求方式不支持
     */
    @ExceptionHandler({IllegalStateException.class})
    public Result<String> men(IllegalStateException e) {
        e.printStackTrace();
        return Result.result("不支持请求", ResultCode.FAILED);
    }

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

    /**
     * If the bean validation is failed, it will trigger a MethodArgumentNotValidException.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpStatus status) {
        List<FieldError> bindingResult = ex.getBindingResult().getFieldErrors();
        Map<String, Object> map = new HashMap<>();
        List<String> list = new LinkedList<>();
        bindingResult.forEach(error -> {
            String field = error.getField();
            Object value = error.getRejectedValue();
            String msg = error.getDefaultMessage();
            list.add(String.format("错误字段: %s 错误值: %s 原因: %s", field, value, msg));
        });
        map.put("msg", list);
        return new ResponseEntity<>(map, status);
    }
}