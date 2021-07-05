package cn.demo.order.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.common.util.RedisUtil;
import cn.demo.order.feign.api.request.OrderReq;
import cn.demo.order.server.OrderBaseService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-15 10:18
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderBaseService orderBaseService;

  @Resource
  private RedisUtil redisUtil;

  /**
   * 创建订单
   */
  @PostMapping("/create")
  public ResponseResult create(@RequestBody OrderReq order) {

    try {
      orderBaseService.createOrder(order);
    } catch (Exception e) {
      log.error("create | {}", e);
      return ResponseResult.fail("创建订单失败");
    }
    return ResponseResult.success("");
  }


  @GetMapping("/redis")
  public ResponseResult redisTest() {

    redisUtil.set("hello", "hello", 60);
    Object result = redisUtil.getStr("hello");
    return ResponseResult.success(result);
  }

}
