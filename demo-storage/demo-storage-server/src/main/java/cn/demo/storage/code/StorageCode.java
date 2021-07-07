package cn.demo.storage.code;

import cn.demo.common.model.base.BaseEnum;

/**
 * @version 1.0
 * @Author 义云
 * @Description 状态码
 * @Date 2020/4/16 16:17
 */
public enum StorageCode implements BaseEnum {

  STORAGE_ID_NOT_FOUND("B30000", "仓库号不存在");;

  /**
   * 状态码
   */
  private String code;
  /**
   * 响应信息
   */
  private String message;

  StorageCode(String code, String message) {

    this.code = code;
    this.message = message;
  }

  @Override
  public String getCode() {

    return code;
  }

  @Override
  public String getMessage() {

    return message;
  }
}
