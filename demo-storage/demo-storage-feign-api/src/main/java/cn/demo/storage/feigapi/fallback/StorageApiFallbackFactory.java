package cn.demo.storage.feigapi.fallback;

import cn.demo.storage.feigapi.dto.StorageSelectDTO;
import cn.demo.storage.feigapi.entity.Storage;
import cn.demo.common.model.base.BaseCode;
import cn.demo.common.model.pojo.RequestObject;
import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.api.StorageApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        return new StorageApi() {
            @Override
            public ResponseResult findById(RequestObject<StorageSelectDTO> requestObject) {
                log.info("findById熔断！",throwable);
                return ResponseResult.fail(BaseCode.REQUEST_TIME_OUT);
            }

            @Override
            public ResponseResult insert(RequestObject<Storage> requestObject) {
                log.info("insert熔断！",throwable);
                return ResponseResult.fail(BaseCode.REQUEST_TIME_OUT);
            }

            @Override
            public ResponseResult deleteById(RequestObject<Storage> requestObject) {
                log.info("deleteById熔断！",throwable);
                return ResponseResult.fail(BaseCode.REQUEST_TIME_OUT);
            }

            @Override
            public ResponseResult update(RequestObject<Storage> requestObject) {
                log.info("update熔断！",throwable);
                return ResponseResult.fail(BaseCode.REQUEST_TIME_OUT);
            }

            @Override
            public ResponseResult findPage(RequestObject<StorageSelectDTO> requestObject) {
                log.info("findPage熔断！",throwable);
                return ResponseResult.fail("服务熔断降级！");
            }
        };
    }
}
