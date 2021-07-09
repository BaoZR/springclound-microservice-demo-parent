package cn.demo.order.server.impl;


import javax.annotation.Resource;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.order.feign.api.entity.OrderBaseEntity;
import cn.demo.order.mapper.OrderBaseMapper;
import cn.demo.order.feign.api.request.OrderReq;
import cn.demo.order.server.OrderBaseService;
import cn.demo.storage.feigapi.api.StorageApi;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author
 * @since 2021-07-05
 */
@Slf4j
@Service
public class OrderBaseServiceImpl extends ServiceImpl<OrderBaseMapper, OrderBaseEntity> implements OrderBaseService {

  @Resource
  private StorageApi storageApi;

  @GlobalTransactional(timeoutMills = 300000, name = "prex-create-order")
  @Override
  public void createOrder(OrderReq order) throws Exception {

    log.info("全局事务，XID = " + RootContext.getXID());

    //远程调用库存服务扣减库存
    log.info("扣减库存开始");

    ResponseResult deduct = storageApi.deduct(order.getProductId(), order.getCount());
    if (!deduct.isSuccess()) {
      log.error("扣减库存失败");
      throw new Exception("扣减库存失败");
    }
    log.info("扣减库存结束");

    //修改订单状态为已完成
    log.info("修改订单状态开始");
    OrderBaseEntity orderEntity = new OrderBaseEntity();
    BeanUtils.copyProperties(order, orderEntity);
    save(orderEntity);
    log.info("修改订单状态结束");
  }

}
