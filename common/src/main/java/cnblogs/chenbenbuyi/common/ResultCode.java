package cnblogs.chenbenbuyi.common;

/**
 * @date: 2020/10/20 16:09
 * @author: chen
 * @desc: 错误码描述
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(0, "成功"),
    FAILED(-1, "失败");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, message);
    }
}