package cn.demo.order.server;


import cn.demo.order.feign.api.request.OrderReq;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-11 17:26
 */
public interface IOrderService {
    void createOrder(OrderReq order) throws Exception ;
}
