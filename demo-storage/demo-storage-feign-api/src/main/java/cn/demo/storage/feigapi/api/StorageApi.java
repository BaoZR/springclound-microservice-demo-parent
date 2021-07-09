package cn.demo.storage.feigapi.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.demo.common.model.pojo.ResponseResult;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:37
 */
@FeignClient(
    path = "/feignApi/storage",
    value = "demo-storage",
    url = "${feign-api.storage.url}")
public interface StorageApi {

  /**
   * 减库存操作
   * @param productId
   * @param count
   */
  @RequestMapping(path = "/deduct", method = RequestMethod.PUT)
  ResponseResult deduct(@RequestParam Integer productId, @RequestParam Integer count);

}
