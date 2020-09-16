package cn.demo.storage.controller;

import cn.demo.common.model.pojo.RequestObject;
import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.code.StorageCode;
import cn.demo.storage.feigapi.api.StorageApi;
import cn.demo.storage.feigapi.dto.StorageSelectDTO;
import cn.demo.storage.feigapi.entity.Storage;
import cn.demo.storage.service.IStorageService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:50
 */
@Slf4j
@RestController
@RequestMapping("/storage")
public class StorageController implements StorageApi {

    @Autowired
    private IStorageService storageService;

    /**
     * @SentinelResource 用于定义资源，并提供可选的异常处理和 fallback 配置项。 @SentinelResource 注解包含以下属性：
     * value：资源名称，必需项（不能为空）
     * entryType：entry 类型，可选项（默认为 EntryType.OUT）
     * blockHandler / blockHandlerClass: blockHandler  对应处理 BlockException 的函数名称，可选项。
     * blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。
     * blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     * fallback：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。
     * fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：返回值类型必须与原函数返回值类型一致；方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     * fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     * defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）
     * 默认 fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。
     * 若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：返回值类型必须与原函数返回值类型一致；
     * 方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     * defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     * 特别地，若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。
     * 若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将 BlockException 直接抛出。
     * @param requestObject
     * @return
     */
    @Override
//    @SentinelResource(value = "sentinel-findById", fallback  = "fallbackHandler", fallbackClass = {
//            ExceptionUtil.class})
    public ResponseResult findById(@RequestBody @Valid RequestObject<StorageSelectDTO> requestObject) {
        String a = "1";
        String b = "2";
        Storage one;
//        try {
            //模拟异常，。。
            if (a.equals(requestObject.getData().getId())){
//                int n = 1/0;
                throw new RuntimeException("findById发生异常");
            }
//        } catch (Exception e) {
//            throw new BaseException(StorageCode.FIND_FAIL);
//        }

        if (b.equals(requestObject.getData().getId())){
            //模拟更细粒度方法限流操作
            one = storageService.selectById(requestObject.getData().getId());
        }else {
            one = storageService.getOne(Wrappers.<Storage>lambdaQuery().eq(Storage::getId, requestObject.getData().getId()));
        }
        return Objects.isNull(one)?ResponseResult.fail(StorageCode.FIND_FAIL):ResponseResult.success(one);
    }
    @Override
    public ResponseResult insert(@RequestBody RequestObject<Storage> requestObject) {
        storageService.save(requestObject.getData());
        return ResponseResult.success();
    }

    @Override
    public ResponseResult deleteById(@RequestBody RequestObject<Storage> requestObject) {
        storageService.removeById(requestObject.getData().getId());
        return ResponseResult.success();
    }

    @Override
    public ResponseResult update(@RequestBody RequestObject<Storage> requestObject) {
        log.info("全局事务，XID = " + RootContext.getXID());
        storageService.updateById(requestObject.getData());
        return ResponseResult.success();
    }

    @Override
    public ResponseResult findPage(@RequestBody RequestObject<StorageSelectDTO> requestObject) {
        PageInfo<Storage> storagePageInfo = PageHelper.startPage(requestObject.getData().getPageNum(), requestObject.getData().getPageSize())
                .doSelectPageInfo(() -> storageService.list());
        return ResponseResult.success(storagePageInfo);
    }

//    @SentinelResource(value = "sentinel-test", fallback  = "fallbackHandler", fallbackClass = {
//            ExceptionUtil.class})
    @PostMapping("/test")
    public ResponseResult test(RequestObject requestObject){
        log.info("test...");
        throw new RuntimeException("发生异常");
    }
}
