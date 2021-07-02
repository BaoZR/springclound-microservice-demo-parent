package cn.demo.common.model.base;

import cn.demo.common.util.LocaleUtils;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 16:10
 */
public enum BaseCode implements BaseEnum {
  /**
   * 操作成功
   */
  OK("00000", "操作成功"),
  /**
   * 操作失败
   */
  FAIL("99999", "操作失败"),
  /**
   * 未知接口
   */
  NO_HANDLER_FOUND("11111", "未知接口"),
  /**
   * 参数校验错误
   */
  VALID_ERROR("22222", "参数校验错误"),
  /**
   * 没有此数据权限
   */
  DATA_EDIT_ERROR("33333", "没有此数据权限"),
  /**
   * 重复请求
   */
  REPEAT_REQUEST("44444", "重复请求"),
  /**
   * 鉴权失败
   */
  AUTHENTICATION_FAIL("55555", "鉴权失败"),
  /**
   * 数据为空
   */
  DATA_IS_NULL("66666", "数据为空"),
  /**
   * 请勿包含非法字符
   */
  INVAILD_SYMBOL("77777", "请勿包含非法字符"),
  /**
   * 熔断降级
   */
  CALL_TIME("88888", "服务调用超时");

  private String code;
  private String message;

  BaseCode(String code, String message) {

    this.code = code;
    this.message = message;
  }

  @Override
  public String getCode() {

    return code;
  }

  @Override
  public String getMessage() {

    String message = LocaleUtils.getMessage(this.code);
    if (this.code.equals(message)) {
      return this.message;
    }
    return message;
  }
}
