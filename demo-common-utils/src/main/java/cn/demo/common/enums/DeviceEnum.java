package cn.demo.common.enums;

public enum DeviceEnum {

    ANDROID("Android"),//安卓
    PC("PC"),//PC
    IOS("IOS"),//IOS
    WAP("wap"),//h5
    WEB("web"),//网站
    ;

    private String type;

    DeviceEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
