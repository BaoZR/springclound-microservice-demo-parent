package cn.demo.common.util.impl;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import cn.demo.common.util.Locker;

/**
 * @Author: jianlong.cui
 * @Date: 2021/7/1
 * @Version: 1.0
 * @Desc
 */
@Component
@ConditionalOnProperty(prefix = "spring.redission-lock", name = "enable", havingValue = "true")
public class RedissonLocker implements Locker {

  @Autowired
  private RedissonClient redissonClient;

  @Override
  public void lock(String lockKey) {
    RLock lock = redissonClient.getLock(lockKey);
    lock.lock();
  }

  @Override
  public void unlock(String lockKey) {
    RLock lock = redissonClient.getLock(lockKey);
    lock.unlock();
  }

  @Override
  public void lock(String lockKey, int leaseTime) {
    RLock lock = redissonClient.getLock(lockKey);
    lock.lock(leaseTime, TimeUnit.SECONDS);
  }

  @Override
  public void lock(String lockKey, TimeUnit unit, int timeout) {
    RLock lock = redissonClient.getLock(lockKey);
    lock.lock(timeout, unit);
  }

  @Override
  public boolean tryLock(String lockKey) {
    RLock lock = redissonClient.getLock(lockKey);
    return lock.tryLock();
  }

  @Override
  public boolean tryLock(String lockKey, long waitTime, long leaseTime,
                         TimeUnit unit) throws InterruptedException {
    RLock lock = redissonClient.getLock(lockKey);
    return lock.tryLock(waitTime, leaseTime, unit);
  }

  @Override
  public boolean isLocked(String lockKey) {
    RLock lock = redissonClient.getLock(lockKey);
    return lock.isLocked();
  }
}
