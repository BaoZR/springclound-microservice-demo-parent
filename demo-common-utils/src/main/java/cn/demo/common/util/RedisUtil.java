package cn.demo.common.util;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020/6/8 22:10
 */
@Slf4j
@Component
public class RedisUtil {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Resource
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * 删除缓存<br>
   * 根据key精确匹配删除
   *
   * @param key
   */
  @SuppressWarnings("unchecked")
  public void del(String... key) {

    if (key != null && key.length > 0) {
      if (key.length == 1) {
        stringRedisTemplate.delete(key[0]);
      } else {
        stringRedisTemplate.delete(CollectionUtils.arrayToList(key));
      }
    }
  }

  /**
   * 批量删除<br>
   * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
   *
   * @param pattern
   */
  public void batchDel(String... pattern) {

    for (String kp : pattern) {
      redisTemplate.delete(redisTemplate.keys(kp + "*"));
    }
  }

  /**
   * 将value对象写入缓存
   *
   * @param key
   * @param value
   * @param time  失效时间(秒)
   */
  public void set(String key, Object value, long time) {

    if (value.getClass().equals(String.class)) {
      stringRedisTemplate.opsForValue().set(key, value.toString());
    } else if (value.getClass().equals(Integer.class)) {
      stringRedisTemplate.opsForValue().set(key, value.toString());
    } else if (value.getClass().equals(Double.class)) {
      stringRedisTemplate.opsForValue().set(key, value.toString());
    } else if (value.getClass().equals(Float.class)) {
      stringRedisTemplate.opsForValue().set(key, value.toString());
    } else if (value.getClass().equals(Short.class)) {
      stringRedisTemplate.opsForValue().set(key, value.toString());
    } else if (value.getClass().equals(Long.class)) {
      stringRedisTemplate.opsForValue().set(key, value.toString());
    } else if (value.getClass().equals(Boolean.class)) {
      stringRedisTemplate.opsForValue().set(key, value.toString());
    } else {
      redisTemplate.opsForValue().set(key, value);
    }
    if (time > 0) {
      redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
  }

  /**
   * 取得缓存（字符串类型）
   *
   * @param key
   * @return
   */
  public String getStr(String key) {

    try {
      return stringRedisTemplate.boundValueOps(key).get();
    } catch (Exception e) {
      return "";
    }
  }


}
