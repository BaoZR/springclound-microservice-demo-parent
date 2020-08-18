package cn.demo.common.model.base;
/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 16:10
 */
public enum BaseCode implements BaseEnum {

    /**
     * 	操作异常
     */
    INTERNAL_SERVER_EXCEPTION(0, "exception"),

    /**
     * 成功
     */
    OK(100000, "成功"),


    /**
     * 失败
     */
    FAIL(999999, "失败"),

    /**
     * 参数校验错误
     */
    VALID_ERROR(900000, "参数校验错误"),

    /**
     * 未知接口
     */
    NO_HANDLER_FOUND(900404, "未知接口"),

    /**
     * Request method not supported
     */
    HTTP_METHOD_NOT_SUPPORT(900405, "Request method not supported"),

    /**
     * Required request body is missing
     */
    REQUIRED_REQUEST_BODY(900406, "Required request body is missing"),

    /**
     * REQUEST TIME OUT
     */
    REQUEST_TIME_OUT(900407, "REQUEST TIME OUT"),

    /**
     * 锁超时
     */
    LOCK_TIME_OUT(900408, "锁超时"),

    /**
     * 没有此数据权限
     */
    DATA_EDIT_ERROR(900409, "没有此数据权限"),

    /**
     * 未获取到锁，业务已经在执行
     */
    NO_LOCK_ACQUIRED(900410, "未获取到锁，业务已经在执行"),
    /**
     * 熔断降级
     */
    CALL_TIME(666,"服务调用超时"),
    ;

    private Integer code;
    private String message;

    BaseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
