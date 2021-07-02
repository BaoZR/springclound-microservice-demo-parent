package cn.demo.common.util;


import lombok.extern.slf4j.Slf4j;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.redission-lock", name = "enable", havingValue = "true")
public class RedisLockUtil {

  @Autowired
  private Locker lk;

  private static Locker locker;

  @PostConstruct
  private void init() {
    locker = lk;
  }

  /**
   * 获取锁
   *
   * @param lockKey
   */
  public static void lock(String lockKey) {
    locker.lock(lockKey);
  }

  /**
   * 释放锁
   *
   * @param lockKey
   */
  public static void unlock(String lockKey) {
    try {
      locker.unlock(lockKey);
    } catch (Exception e) {
      log.info("unlock , err:", e);
    }
  }

  /**
   * 获取锁，超时释放
   *
   * @param lockKey
   * @param timeout
   */
  public static void lock(String lockKey, int timeout) {
    locker.lock(lockKey, timeout);
  }

  /**
   * 获取锁，超时释放，指定时间单位
   *
   * @param lockKey
   * @param unit
   * @param timeout
   */
  public static void lock(String lockKey, TimeUnit unit, int timeout) {
    locker.lock(lockKey, unit, timeout);
  }

  /**
   * 尝试获取锁，获取到立即返回true,获取失败立即返回false
   *
   * @param lockKey
   * @return
   */
  public static boolean tryLock(String lockKey) {
    return locker.tryLock(lockKey);
  }

  /**
   * 尝试获取锁，在给定的waitTime时间内尝试，获取到返回true,获取失败返回false,获取到后再给定的leaseTime时间超时释放
   *
   * @param lockKey
   * @param waitTime
   * @param leaseTime
   * @param unit
   * @return
   * @throws InterruptedException
   */
  public static boolean tryLock(String lockKey, long waitTime, long leaseTime,
                                TimeUnit unit) throws InterruptedException {
    return locker.tryLock(lockKey, waitTime, leaseTime, unit);
  }

  /**
   * 锁释放被任意一个线程持有
   *
   * @param lockKey
   * @return
   */
  public static boolean isLocked(String lockKey) {
    return locker.isLocked(lockKey);
  }

}
