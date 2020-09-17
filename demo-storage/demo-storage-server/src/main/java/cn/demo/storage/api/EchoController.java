package cn.demo.storage.api;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.api.EchoApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 义云
 * @Date: 2020/9/17 3:39 PM
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/feignApi/echo")
public class EchoController implements EchoApi {
    @Override
    public ResponseResult echo1(String str) {
        return ResponseResult.success("Hello," + str);
    }

    @Override
    public ResponseResult echo2() {
        return ResponseResult.success("echo2");
    }
}
