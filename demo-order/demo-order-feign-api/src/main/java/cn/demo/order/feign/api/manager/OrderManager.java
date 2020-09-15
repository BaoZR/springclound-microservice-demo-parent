package cn.demo.order.feign.api.manager;

import cn.demo.order.feign.api.entity.OrderEntity;
import cn.demo.order.feign.api.mapper.OrderEntityMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 义云
 * @Date: 2020/9/15 2:07 PM
 * @Version 1.0
 */
@Component
public class OrderManager {
    @Resource
    private OrderEntityMapper mapper;

    public void add(OrderEntity orderEntity) {
        mapper.insert(orderEntity);
    }
}
