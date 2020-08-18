package cn.demo.order.manager;

import cn.demo.order.mapper.demoOrderEntityMapper;
import cn.demo.order.model.entity.demoOrderEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-11 17:19
 */
@Component
public class OrderManager {
    @Resource
    private demoOrderEntityMapper mapper;

    public void add(demoOrderEntity orderEntity) {
        mapper.insertSelective(orderEntity);
    }
}
