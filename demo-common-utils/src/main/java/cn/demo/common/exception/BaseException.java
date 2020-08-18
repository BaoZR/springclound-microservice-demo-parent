package cn.demo.common.exception;


import cn.demo.common.model.base.BaseCode;
import cn.demo.common.model.base.BaseEnum;

/**
 * @version 1.0
 * @Author 义云
 * @Description 自定义异常
 * @Date 2020/4/16 17:05
 */
public class BaseException extends RuntimeException implements BaseEnum {
	private static final long serialVersionUID = 1L;
	
    private Integer code;

    private String message;

    public BaseException(String message) {
		super(message);
		this.code = BaseCode.FAIL.getCode();
		this.message = message;
	}
	
	public BaseException(String message, Throwable e) {
		super(message, e);
		this.code = BaseCode.FAIL.getCode();
		this.message = message;
	}
	
	public BaseException(String message, Integer code) {
		super(message);
		this.message = message;
		this.code = code;
	}

	public BaseException(Integer code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}
	
	public BaseException(String message, Integer code, Throwable e) {
		super(message, e);
		this.message = message;
		this.code = code;
	}
	
	public BaseException(BaseEnum baseEnum, Throwable e) {
		super(baseEnum.getMessage(), e);
		this.message = baseEnum.getMessage();
		this.code = baseEnum.getCode();
	}
	
	public BaseException(BaseEnum baseEnum, String error) {
		super(error);
		this.message = error;
		this.code = baseEnum.getCode();
	}
	
	public BaseException(BaseEnum baseEnum) {
		super(baseEnum.getMessage());
		this.message = baseEnum.getMessage();
		this.code = baseEnum.getCode();
	}

	@Override
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
