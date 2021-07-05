package cn.demo.storage.feigapi.fallback;

import cn.demo.common.model.base.BaseCode;
import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.api.EchoApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 义云
 * @Date: 2020/9/17 3:23 PM
 * @Version 1.0
 */
@Slf4j
@Component
public class EchoServiceFallbackFactory implements FallbackFactory<EchoApi> {
    @Override
    public EchoApi create(Throwable throwable) {
        // 降级处理
        return new EchoApi() {
            @Override
            public ResponseResult echo1(String str) {
                log.info("触发熔断，降级处理！", throwable);
                return ResponseResult.fail(BaseCode.CALL_TIME);
            }

            @Override
            public ResponseResult echo2() {
                log.info("触发熔断，降级处理！", throwable);
                return ResponseResult.fail(BaseCode.CALL_TIME);
            }
        };

    }
}
