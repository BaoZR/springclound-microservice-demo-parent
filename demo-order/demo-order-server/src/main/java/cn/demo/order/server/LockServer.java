package cn.demo.order.server;

import cn.demo.common.util.RedisLockUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LockServer {
    @Resource
    private RedisLockUtil redissonLockUtil;

    public void local() {
        redissonLockUtil.fairLock("hello", 30);
    }

    public void onlock() {
        redissonLockUtil.release("hello");
    }
}
