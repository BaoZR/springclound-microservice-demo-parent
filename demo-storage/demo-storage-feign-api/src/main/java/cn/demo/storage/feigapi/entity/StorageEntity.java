package cn.demo.storage.feigapi.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 仓储服务
 * </p>
 *
 * @author 
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("storage")
public class StorageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Integer id;

    /**
     * 商品Id
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 库存数量
     */
    @TableField("count")
    private Integer count;


}
