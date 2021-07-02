package cn.demo.common.enums;

import lombok.Getter;

@Getter
public enum DeviceEnum {

  /**
   *
   */
  ANDROID("Android"),
  PC("PC"),
  IOS("IOS"),
  WAP("wap"),
  WEB("web"),
  ;

  private String type;

  DeviceEnum(String type) {

    this.type = type;
  }
}
