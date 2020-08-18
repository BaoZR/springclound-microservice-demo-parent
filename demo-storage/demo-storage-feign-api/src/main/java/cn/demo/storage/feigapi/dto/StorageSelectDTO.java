package cn.demo.storage.feigapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:45
 */
@Data
public class StorageSelectDTO {
    @NotBlank(message = "id不能为空")
    private String id;

    private Integer pageNum;

    private Integer pageSize;
}
