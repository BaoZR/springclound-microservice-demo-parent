package cn.demo.storage.service.impl;


import cn.demo.storage.feigapi.entity.StorageEntity;
import cn.demo.storage.mapper.StorageMapper;
import cn.demo.storage.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

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

}
