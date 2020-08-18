package cn.demo.storage.code;

import cn.demo.common.model.base.BaseEnum;

/**
 * @version 1.0
 * @Author 义云
 * @Description 状态码
 * @Date 2020/4/16 16:17
 */
public enum StorageCode implements BaseEnum {

    /**
     * 测试
     */
    TEST(666,"测试"),
    FIND_FAIL(777,"查询失败！"),
    ADD_SUCCESS(888,"添加成功！"),
    CURRENT_LIMITING(999,"服务器繁忙（接口限流）"),
    FUSING(998,"服务器繁忙（熔断降级）"),
    ;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;

    StorageCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
