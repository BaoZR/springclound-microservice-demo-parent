package cn.demo.common.util;


import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.redission-lock", name = "enable", havingValue = "true")
public class RedisLockUtil {
    @Autowired
    RedissonClient redissonClient;

    /**
     *
     * 加锁操作(可重入锁)
     *
     * @return
     */
    public boolean tryLock(String lockName, long expireSeconds) {
        RLock rLock = redissonClient.getLock(lockName);
        boolean getLock = false;
        try {
            // 尝试加锁，最多等待1秒，上锁以后 expireSeconds 秒自动解锁
            getLock = rLock.tryLock(1, expireSeconds, TimeUnit.SECONDS);
            if (getLock) {
                log.info("获取Redisson tryLock 分布式锁成功,lockName: {}", lockName);
            } else {
                log.error("获取Redisson tryLock 分布式锁失败,lockName: {}", lockName);
            }
        } catch (InterruptedException e) {
            log.error("获取Redisson tryLock 分布式锁异常，lockName: {}, err: {}" + lockName, e);
            return false;
        }
        return getLock;
    }

    /**
     * 加锁操作(公平锁)
     * @param lockName
     * @param expireSeconds
     * @return
     */
    public boolean fairLock(String lockName, long expireSeconds){
        RLock fairLock = redissonClient.getFairLock(lockName);
        boolean getLock = false;
        try {
            // 尝试加锁，最多等待1秒，上锁以后 expireSeconds 秒自动解锁
            getLock = fairLock.tryLock(1, expireSeconds, TimeUnit.SECONDS);
            if (getLock) {
                log.info("获取Redisson fairLock 分布式锁成功,lockName: {}", lockName);
            } else {
                log.error("获取Redisson fairLock 分布式锁失败,lockName: {}", lockName);
            }
        }catch (InterruptedException e){
            log.error("获取Redisson fairLock 分布式锁异常，lockName: {}, err: {}" + lockName, e);
            return false;
        }
        return getLock;
    }


    /**
     * 读写锁(读锁)
     * @param lockName
     * @param expireSeconds
     * @return
     */
    public boolean readLock(String lockName, long expireSeconds){
        RReadWriteLock rwlock = redissonClient.getReadWriteLock(lockName);
        boolean getLock = false;
        try {
            // 尝试加锁，最多等待1秒，上锁以后 expireSeconds 秒自动解锁
            getLock = rwlock.readLock().tryLock(1, expireSeconds, TimeUnit.SECONDS);
            if (getLock) {
                log.info("获取Redisson readLock 分布式锁成功,lockName: {}", lockName);
            } else {
                log.error("获取Redisson readLock 分布式锁失败,lockName: {}", lockName);
            }
        }catch (InterruptedException e){
            log.error("获取Redisson readLock 分布式锁异常，lockName: {}, err: {}" + lockName, e);
            return false;
        }
        return getLock;
    }

    /**
     * 读写锁(写锁)
     * @param lockName
     * @param expireSeconds
     * @return
     */
    public boolean writeLock(String lockName, long expireSeconds){
        RReadWriteLock rwlock = redissonClient.getReadWriteLock(lockName);
        boolean getLock = false;
        try {
            // 尝试加锁，最多等待1秒，上锁以后 expireSeconds 秒自动解锁
            getLock = rwlock.writeLock().tryLock(1, expireSeconds, TimeUnit.SECONDS);
            if (getLock) {
                log.info("获取Redisson writeLock 分布式锁成功,lockName: {}", lockName);
            } else {
                log.error("获取Redisson writeLock 分布式锁失败,lockName: {}", lockName);
            }
        }catch (InterruptedException e){
            log.error("获取Redisson writeLock 分布式锁异常，lockName: {}, err: {}" + lockName, e);
            return false;
        }
        return getLock;
    }



    /**
     * 解锁
     *
     * @param lockName
     */
    public void release(String lockName) {
        try {
            redissonClient.getLock(lockName).unlock();
        }catch (Exception e){
            log.error("解锁失败: {}", e);
        }
    }



}
