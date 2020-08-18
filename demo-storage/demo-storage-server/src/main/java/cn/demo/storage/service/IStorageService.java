package cn.demo.storage.service;

import cn.demo.storage.feigapi.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 15:33
 */
public interface IStorageService extends IService<Storage> {

    Storage selectById(String id);
}
