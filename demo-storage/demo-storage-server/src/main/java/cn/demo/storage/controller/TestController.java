package cn.demo.storage.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 义云
 * @Date: 2020/9/17 10:20 AM
 * @Version 1.0
 */
@Slf4j
@RestController
@RefreshScope
public class TestController {

  /**
   * 如果要用nacos配置中心获取动态字段，使用使用注解：@RefreshScope
   */
  @Value("${storage.param1}")
  private String param;


  @GetMapping("/getparam")
  public String dynamic() {

    return param;
  }

}
