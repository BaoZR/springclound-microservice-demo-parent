package cn.demo.order.controller;

import cn.demo.order.feign.api.request.OrderReq;
import cn.demo.order.server.IOrderService;
import cn.demo.order.server.LockServer;
import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-15 10:18
 */
@Slf4j
@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private IOrderService iOrderService;

    @Resource
    private LockServer lockServer;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResponseResult create(@RequestBody OrderReq order) {
        try {
            iOrderService.createOrder(order);
        } catch (Exception e) {
            log.error("create | {}", e);
            return ResponseResult.fail("创建订单失败");
        }
        return ResponseResult.success("");
    }

    @GetMapping("/lock")
    public ResponseResult local() throws InterruptedException {
        lockServer.local();
        Thread.sleep(300);
        //lockServer.onlock();
        return ResponseResult.success("");
    }

    @GetMapping("/onlock")
    public ResponseResult onlock() {
        lockServer.onlock();
        return ResponseResult.success("");
    }

    @GetMapping("/redis")
    public  ResponseResult redisTest() {
        redisUtil.set("hello", "hello", 60);
        Object result = redisUtil.getStr("hello");
        return ResponseResult.success(result);
    }
}
