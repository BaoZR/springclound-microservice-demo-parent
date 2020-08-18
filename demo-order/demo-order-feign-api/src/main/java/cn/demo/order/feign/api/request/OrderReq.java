package cn.demo.order.feign.api.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-15 10:20
 */
@Data
public class OrderReq {
    /**
     * Database Column Remarks:
     *   用户Id
     *
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * Database Column Remarks:
     *   付款金额
     *

     *
     * @mbg.generated
     */
    private BigDecimal payMoney;

    /**
     * Database Column Remarks:
     *   商品Id
     *
     *
     * @mbg.generated
     */
    private Integer productId;

    /**
     * Database Column Remarks:
     *   状态
     *
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * Database Column Remarks:
     *   商品数量
     *

     */
    private Integer count;
}
