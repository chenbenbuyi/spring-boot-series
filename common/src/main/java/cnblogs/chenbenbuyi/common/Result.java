package cnblogs.chenbenbuyi.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @date: 2020/10/20 16:32
 * @author: chen
 * @desc: 统一返回数据模型
 */

@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private long code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 内容数据
     */
    private T data;

    public Result() {
    }

    public Result(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> Result<T> ok() {
        return result(null, ResultCode.SUCCESS);
    }

    public static <T> Result<T> ok(T data) {
        return result(data, ResultCode.SUCCESS);
    }

    public static <T> Result<T> failed() {
        return result(ResultCode.FAILED);
    }

    public static <T> Result<T> result(IErrorCode errorCode) {
        return result(null, errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> Result<T> result(T data, IErrorCode errorCode) {
        return result(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> Result<T> result(T data, long code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
}
