package cn.demo.storage.feigapi.entity;

import cn.demo.common.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @version 1.0
 * @Author 义云
 * @Description DEMO
 * @Date 2020/4/16 14:33
 */
@Data
@TableName(value = "storage")
public class Storage extends BaseEntity {

    private Integer storageId;

    private BigDecimal price;

    private Integer count;

    @TableField(value = "is_delete",fill = FieldFill.INSERT)
    private Integer isDelete;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", storageId=").append(storageId);
        sb.append(", price=").append(price);
        sb.append(", count=").append(count);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Storage other = (Storage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getStorageId() == null ? other.getStorageId() == null : this.getStorageId().equals(other.getStorageId()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStorageId() == null) ? 0 : getStorageId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        return result;
    }
}