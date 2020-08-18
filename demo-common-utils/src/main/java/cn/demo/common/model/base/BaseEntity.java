package cn.demo.common.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @Author 义云
 * @Description 抽象通用实体类
 * @Date 2020/4/17 13:41
 */
@Data
public class BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    protected String id;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    protected Date createTime;

    @TableField(value = "modify_date",fill = FieldFill.INSERT_UPDATE)
    protected Date modifyDate;
}
