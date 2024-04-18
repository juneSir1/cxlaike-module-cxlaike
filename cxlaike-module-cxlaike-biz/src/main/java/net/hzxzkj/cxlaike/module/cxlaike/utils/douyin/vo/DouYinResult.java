package net.hzxzkj.cxlaike.module.cxlaike.utils.douyin.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;
import net.hzxzkj.cxlaike.framework.common.exception.ServiceException;
import net.hzxzkj.cxlaike.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author jianan.han
 * @date 2023-08-17 11:19
 * @description
 */
@Data
public class DouYinResult<T> implements Serializable {

    /**
     * 错误码
     *
     * @see ErrorCode#getCode()
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示，用户可阅读
     *
     * @see ErrorCode#getMsg() ()
     */
    private String message;

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> DouYinResult<T> error(DouYinResult<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    public static <T> DouYinResult<T> error(Integer code, String message) {
        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.getCode().equals(code), "code 必须是错误的！");
        DouYinResult<T> result = new DouYinResult<>();
        result.code = code;
        result.message = message;
        return result;
    }

    public static <T> DouYinResult<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> DouYinResult<T> success(T data) {
        DouYinResult<T> result = new DouYinResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.message = "";
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode());
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isSuccess() {
        return isSuccess(code);
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isError() {
        return !isSuccess();
    }

    // ========= 和 Exception 异常体系集成 =========

    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     */
    public void checkError() throws ServiceException {
        if (isSuccess()) {
            return;
        }
        // 业务异常
        throw new ServiceException(code, message);
    }

    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     * 如果没有，则返回 {@link #data} 数据
     */
    @JsonIgnore // 避免 jackson 序列化
    public T getCheckedData() {
        checkError();
        return data;
    }

    public static <T> DouYinResult<T> error(ServiceException serviceException) {
        return error(serviceException.getCode(), serviceException.getMessage());
    }
}