package cn.demo.order.server.impl;

import cn.demo.order.feign.api.request.OrderReq;
import cn.demo.order.manager.OrderManager;
import cn.demo.order.model.entity.demoOrderEntity;
import cn.demo.order.server.IOrderService;
import cn.demo.storage.feigapi.entity.Storage;
import cn.demo.common.model.pojo.RequestObject;
import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.api.StorageApi;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-11 17:33
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderManager orderManager;


    @Resource
    private StorageApi storageApi;


    @GlobalTransactional(timeoutMills = 300000, name = "prex-create-order")
    @Override
    public void createOrder(OrderReq order) throws Exception {
        log.info("全局事务，XID = " + RootContext.getXID());

        //远程调用库存服务扣减库存
        log.info("扣减库存开始");

        Storage storage = new Storage();
        storage.setCount(22);
        storage.setStorageId(23);
        RequestObject<Storage> requestObject = new RequestObject<>();
        requestObject.setData(storage);
        ResponseResult responseResult = storageApi.update(requestObject);
        if (!responseResult.isSuccess()) {
            log.error("扣减库存失败");
            throw new Exception("扣减库存失败");
        }
        log.info("扣减库存结束");

        //修改订单状态为已完成
        log.info("修改订单状态开始");
        demoOrderEntity orderEntity = new demoOrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        orderManager.add(orderEntity);
        log.info("修改订单状态结束");
    }
}
