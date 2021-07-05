package cn.demo.order.feign.api.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_base")
public class OrderBaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键Id
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /**
   * 用户Id
   */
  @TableField("user_id")
  private Integer userId;

  /**
   * 付款金额
   */
  @TableField("pay_money")
  private BigDecimal payMoney;

  /**
   * 商品Id
   */
  @TableField("product_id")
  private Integer productId;

  /**
   * 状态
   */
  @TableField("status")
  private Integer status;

  /**
   * 商品数量
   */
  @TableField("count")
  private Integer count;


}
