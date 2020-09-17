package cn.demo.order.controller;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.api.EchoApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 义云
 * @Date: 2020/9/17 3:45 PM
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping
public class TestEchoController {
    @Autowired
    private EchoApi echoApi;

    @GetMapping("/echo1/{str}")
    public ResponseResult testEcho1(@PathVariable("str") String str) {
        return echoApi.echo1(str);
    }

    @GetMapping("/echo2")
    public ResponseResult testEcho2() {
        return echoApi.echo2();
    }
}
