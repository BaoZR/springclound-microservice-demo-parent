package cn.demo.storage.feigapi.api;

import cn.demo.storage.feigapi.dto.StorageSelectDTO;
import cn.demo.storage.feigapi.entity.Storage;
import cn.demo.storage.feigapi.fallback.StorageApiFallbackFactory;
import cn.demo.common.model.pojo.RequestObject;
import cn.demo.common.model.pojo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:37
 */
@FeignClient(path = "storage", value = "demo-storage",fallbackFactory = StorageApiFallbackFactory.class)
public interface StorageApi {

    /**
     * 根据ID查询
     * @param requestObject
     * @return
     */
    @RequestMapping(path = "/findById", method = RequestMethod.POST)
    ResponseResult findById(@RequestBody RequestObject<StorageSelectDTO> requestObject);

    /**
     * 添加
     * @param requestObject
     * @return
     */
    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    ResponseResult insert(@RequestBody RequestObject<Storage> requestObject);

    /**
     * 删除
     * @param requestObject
     * @return
     */
    @RequestMapping(path = "/deleteById", method = RequestMethod.POST)
    ResponseResult deleteById(@RequestBody RequestObject<Storage> requestObject);
    /**
     * 修改
     * @param requestObject
     * @return
     */
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    ResponseResult update(@RequestBody RequestObject<Storage> requestObject);

    /**
     * 分页查询
     * @param requestObject
     * @return
     */
    @RequestMapping(path = "/findPage", method = RequestMethod.POST)
    ResponseResult findPage(@RequestBody RequestObject<StorageSelectDTO> requestObject);
}
