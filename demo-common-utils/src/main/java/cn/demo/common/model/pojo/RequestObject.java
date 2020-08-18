package cn.demo.common.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestObject<T> {
    @Valid
    private T data;
}
