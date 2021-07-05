package cn.demo.order.server;

import cn.demo.order.feign.api.entity.OrderBaseEntity;
import cn.demo.order.feign.api.request.OrderReq;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author 
 * @since 2021-07-05
 */
public interface OrderBaseService extends IService<OrderBaseEntity> {
  void createOrder(OrderReq order) throws Exception ;

}
