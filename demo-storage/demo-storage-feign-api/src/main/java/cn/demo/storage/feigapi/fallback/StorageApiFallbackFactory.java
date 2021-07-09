package cn.demo.storage.feigapi.fallback;

import org.springframework.stereotype.Component;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.api.StorageApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @Author 义云
 * @Description 熔断
 * @Date 2020/4/21 19:57
 */
@Component
@Slf4j
public class StorageApiFallbackFactory implements FallbackFactory<StorageApi> {


  @Override
  public StorageApi create(Throwable throwable) {
    // 对所有方法做降级处理
    return new StorageApi() {
      @Override
      public ResponseResult deduct(Integer productId, Integer count) {

        return null;
      }
    };
  }

}
