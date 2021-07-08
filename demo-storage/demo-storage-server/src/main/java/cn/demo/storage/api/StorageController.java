package cn.demo.storage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.demo.common.model.pojo.ResponseResult;
import cn.demo.storage.feigapi.api.StorageApi;
import cn.demo.storage.feigapi.dto.StorageSelectDTO;
import cn.demo.storage.feigapi.entity.StorageEntity;
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
public class StorageController implements StorageApi {

  @Autowired
  private StorageService storageService;

  @Override
  public ResponseResult findById(StorageSelectDTO data) {

    return null;
  }

  @Override
  public ResponseResult insert(StorageEntity data) {

    return null;
  }

  @Override
  public ResponseResult deleteById(Integer id) {

    return null;
  }

  @Override
  public ResponseResult update(StorageEntity data) {

    return null;
  }

  @Override
  public ResponseResult findPage(StorageSelectDTO data) {

    return null;
  }

}
