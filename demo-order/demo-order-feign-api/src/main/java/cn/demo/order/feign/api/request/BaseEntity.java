package cn.demo.order.feign.api.request;

import lombok.Builder;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-17 14:56
 */
@Builder
@Data
public class BaseEntity {
    private Integer id;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer rows = 10;
}
