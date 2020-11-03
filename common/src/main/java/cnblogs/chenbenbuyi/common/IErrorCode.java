package cnblogs.chenbenbuyi.common;

/**
 * @date: 2020/10/20 16:10
 * @desc:
 */
public interface IErrorCode {
    /**
     * 错误编码
     */
    long getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
