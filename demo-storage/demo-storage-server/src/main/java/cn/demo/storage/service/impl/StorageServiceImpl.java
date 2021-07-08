package cn.demo.storage.service.impl;


import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.demo.storage.feigapi.entity.StorageEntity;
import cn.demo.storage.mapper.StorageMapper;
import cn.demo.storage.service.StorageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 仓储服务 服务实现类
 * </p>
 *
 * @author
 * @since 2021-07-07
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, StorageEntity> implements StorageService {


  /**
   * 减库存
   *
   * @param productId
   * @param count
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deduct(Integer productId, int count) {

    LambdaQueryWrapper<StorageEntity> wrapper = Wrappers.<StorageEntity>lambdaQuery().eq(StorageEntity::getProductId, productId);
    StorageEntity storageEntity = getOne(wrapper);
    if (Objects.isNull(storageEntity) || (storageEntity.getCount() - count) < 0) {
      throw new RuntimeException("库存不足");
    }
    storageEntity.setCount(storageEntity.getCount() - count);
    updateById(storageEntity);
  }

}
