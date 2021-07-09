package cn.demo.storage.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.api.StorageApi;
import cn.demo.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:50
 */
@Slf4j
@RestController
@RequestMapping("/feignApi/storage")
public class StorageController implements StorageApi {

  @Resource
  private StorageService storageService;

  @Override
  public ResponseResult deduct(Integer productId, Integer count) {

    storageService.deduct(productId, count);
    log.info("减库存成功");
    return ResponseResult.success();
  }

}
