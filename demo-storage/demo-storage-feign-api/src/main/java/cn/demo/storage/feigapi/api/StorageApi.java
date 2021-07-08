package cn.demo.storage.feigapi.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.dto.StorageSelectDTO;
import cn.demo.storage.feigapi.entity.StorageEntity;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:37
 */
@FeignClient(
    path = "/feignApi/storage",
    value = "demo-storage",
    url = "${feign-api.category.url:127.0.0.1:40001}")
public interface StorageApi {

  /**
   * 根据ID查询
   *
   * @param data
   * @return
   */
  @RequestMapping(path = "/findById", method = RequestMethod.POST)
  ResponseResult findById(@RequestBody StorageSelectDTO data);

  /**
   * 添加
   *
   * @param data
   * @return
   */
  @RequestMapping(path = "/insert", method = RequestMethod.POST)
  ResponseResult insert(@RequestBody StorageEntity data);

  /**
   * 删除
   *
   * @param
   * @return
   */
  @RequestMapping(path = "/deleteById", method = RequestMethod.POST)
  ResponseResult deleteById(@RequestBody Integer id);

  /**
   * 修改
   *
   * @param data
   * @return
   */
  @RequestMapping(path = "/update", method = RequestMethod.POST)
  ResponseResult update(@RequestBody StorageEntity data);

  /**
   * 分页查询
   *
   * @param data
   * @return
   */
  @RequestMapping(path = "/findPage", method = RequestMethod.POST)
  ResponseResult findPage(@RequestBody StorageSelectDTO data);

}
