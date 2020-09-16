package cn.demo.storage.service.impl;

import cn.demo.common.exception.BaseException;
import cn.demo.common.model.base.BaseCode;
import cn.demo.storage.code.StorageCode;
import cn.demo.storage.dao.StorageMapper;
import cn.demo.storage.feigapi.entity.Storage;
import cn.demo.storage.service.IStorageService;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author 义云
 * @Description demo
 * @Date 2020/4/16 15:33
 */
@Service
@Slf4j
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {


    @Override
    //@SentinelResource(value = "sentinel-selectById", fallback = "fallbackHandler")
    public Storage selectById(String id) {

        throw new RuntimeException("测试熔断。。");
        //return baseMapper.selectById(id);
    }
    public Storage blockExceptionSelectById(String id, BlockException e) {
        log.info("Oops blockExceptionSelectById: " + e.getClass().getCanonicalName()+",ID:{}",id);
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            log.info("sentinel-selectById限流了");
            throw new BaseException(StorageCode.CURRENT_LIMITING);
        }
        throw new BaseException(BaseCode.CALL_TIME);
    }
    public  Storage fallbackHandler(String id, Throwable e) {
        log.info("Oops fallbackHandler: " + e.getClass().getCanonicalName());
        // 不同的异常返回不同的提示语
        if (e instanceof DegradeException) {
            log.info("sentinel-selectById服务降级了");
            throw new BaseException(StorageCode.FUSING);
        }
        throw new BaseException(BaseCode.CALL_TIME);
    }
}
