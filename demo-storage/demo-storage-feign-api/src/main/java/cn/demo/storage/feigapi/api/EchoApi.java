package cn.demo.storage.feigapi.api;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.fallback.EchoServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: 义云
 * @Date: 2020/9/17 3:21 PM
 * @Version 1.0
 */
// 关于 contexId 的配置，可以参考这篇博客： https://juejin.im/post/6844904039595917319
@FeignClient(
        path = "/feignApi/echo",
        value = "demo-storage",
        contextId = "echo",
        fallbackFactory = EchoServiceFallbackFactory.class)
public interface EchoApi {
    /**
     * 调用服务提供方的输出接口
     *
     * @param str 用户输入
     * @return
     */
    @GetMapping(value = "/echo1/{str}")
    ResponseResult echo1(@PathVariable("str") String str);


    @GetMapping(value = "echo2")
    ResponseResult echo2();

}
