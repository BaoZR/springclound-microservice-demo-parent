package cn.demo.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version 1.0
 * @Author 义云
 * @Description mybatis-plus自动填充 各服务模块启动类中必须配置扫描路径才起作用@SpringBootApplication(scanBasePackages = "cn.demo")
 * @Date 2020/4/17 9:58
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("==========start insert fill ....");
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject,"isDelete",Integer.class,Integer.valueOf(0));
        this.strictUpdateFill(metaObject, "modifyDate", Date.class, new Date());
    }

    /**
     *         下面的已过时(注意 strictUpdateFill 有多个方法,详细查看源码)
     *         this.setFieldValByName("operator", "Tom", metaObject);
     *         this.setUpdateFieldValByName("operator", "Tom", metaObject);
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("=============start update fill ....");
        // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "modifyDate", Date.class, new Date());
    }
}
