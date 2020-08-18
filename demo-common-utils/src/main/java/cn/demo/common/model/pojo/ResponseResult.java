package cn.demo.common.model.pojo;

import cn.demo.common.model.base.BaseCode;
import cn.demo.common.model.base.BaseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:43
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@SuppressWarnings("all")
public class ResponseResult<T> implements Serializable {

	private static final long serialVersionUID = -2073390651767969040L;

    private Integer code = BaseCode.OK.getCode();
    private String message = BaseCode.OK.getMessage();
    private T data;

    public ResponseResult(BaseCode baseCode){
        super();
        this.code = baseCode.getCode();
        this.message = baseCode.getMessage();
    }
    public ResponseResult(BaseEnum baseEnum){
        super();
        this.code = baseEnum.getCode();
        this.message = baseEnum.getMessage();
    }
    public ResponseResult(T data) {
        super();
        this.data = data;
    }

    /**
     * 第一个函数式接口参数执行成功返回时的逻辑。
     * 如果有第二个函数式接口参数，则异常返回时执行；否则返回当前对象本身
     * @param service
     * @return
     */
    public ResponseResult success(Supplier... service) {
        if (isSuccess()) {
            return new ResponseResult((service[0].get()));
        } else if (service.length > 1){
            return new ResponseResult(service[1].get());
        }
        return this;
    }

    public ResponseResult(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    /**
     * 提供统一校验接口是否正常返回的方法
     */
    public Boolean isSuccess() {
        if (this.code.equals(BaseCode.OK.getCode())) {
            return true;
        }
        return false;
    }
    public static <T> ResponseResult success(){
        return new ResponseResult();
    }
    /**
     * 返回成功数据（status：100000）
     *
     * @param data 数据内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(BaseCode.OK.getCode()).setMessage(BaseCode.OK.getMessage()).setData(data);
    }

    /**
     * 返回错误数据（status：0）
     *
     * @param data 错误内容
     * @param <T>  数据类型
     * @return ResponseResult实例
     */
    public static <T> ResponseResult fail(T data) {
        if (data instanceof BaseEnum){
            BaseEnum baseEnum = (BaseEnum)data;
            return new ResponseResult(baseEnum);
        }
        return new ResponseResult().setCode(BaseCode.INTERNAL_SERVER_EXCEPTION.getCode()).setMessage(BaseCode.INTERNAL_SERVER_EXCEPTION.getMessage()).setData(data);
    }

    /**
     * 自定义返回错误数据
     *
     * @param code    错误代码
     * @param message 错误消息
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code, String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * @param code    错误代码
     * @param message 错误消息
     * @param data    错误内容
     * @return ResponseResult实例
     */
    public static ResponseResult fail(int code, String message, String data) {
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
    /**
     * @param code    错误代码
     * @param message 错误消息
     * @param data   枚举
     * @return ResponseResult实例
     */
    public static ResponseResult fail(BaseCode data) {
        return new ResponseResult().setCode(data.getCode()).setMessage(data.getMessage());
    }
}