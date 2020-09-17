package cn.demo.storage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 义云
 * @Date: 2020/9/17 10:20 AM
 * @Version 1.0
 */
@Slf4j
@RestController
public class TestController {
    @GetMapping("/dynamic")
    public String dynamic() {
        return "Hello Dynamic Rules";
    }

}
