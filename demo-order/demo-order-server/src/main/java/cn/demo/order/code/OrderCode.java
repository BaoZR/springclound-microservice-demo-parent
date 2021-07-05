package cn.demo.order.code;

import cn.demo.common.model.base.BaseEnum;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-17 14:37
 */
public enum OrderCode implements BaseEnum {
    ORDER_ID_NOT_FOUND("A30000", "订单号不存在");

    /**
     * 状态码
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;

    OrderCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
