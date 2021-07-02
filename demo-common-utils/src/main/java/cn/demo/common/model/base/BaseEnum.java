package cn.demo.common.model.base;

/**
 * 系统返回码统一接口
 * 系统返回码标准：返回码由 数字编码及返回信息构成，即 code & message
 * message：各个返回编码的含义，用于前端提示或者前端释义（国际化）。
 * code：由6位数字组成，正常情况返回 00000，未知异常情况返回 999999
 *  code定义标准：
 *      A开头为
 *      B开头为
 *      C开头为
 *      D开头为
 *      E开头为
 *      F开头为
 *
 * 可以参考阿里巴巴开发手册（嵩山版）
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:50
 */
public interface BaseEnum {

  /**
   * 获取状态码
   * @return
   */
  String getCode();

  /**
   * 获取友好提示信息
   * @return
   */
  String getMessage();

  /**
   * 成功
   * @return boolean
   */
  default Boolean success() {

    if (BaseCode.OK.getCode().equals(this.getCode())) {
      return true;
    }
    return false;
  }

}
