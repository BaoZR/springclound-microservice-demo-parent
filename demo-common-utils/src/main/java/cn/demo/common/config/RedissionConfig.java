//package cn.demo.common.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import lombok.Data;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.redisson.spring.data.connection.RedissonConnectionFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.io.IOException;
//
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "spring.redission")
//public class RedissionConfig {
//    private String host;
//    private String password;
//    private int port;
//    private int database;
//    private int timeout;
//    private int maxActive;
//    private int maxIdle;
//    private int minIdle;
//    private long maxWait;
//
//    @Bean("redissionTemp")
//    public RedisTemplate<String, Object> getRedisTemplate(@Qualifier("redissionConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//
//    @Bean(name = "redissionConnectionFactory")
//    public RedissonConnectionFactory redissonConnectionFactory(@Qualifier("redisson") RedissonClient redisson) {
//        return new RedissonConnectionFactory(redisson);
//    }
//
//
//    @Bean(name = "redisson", destroyMethod = "shutdown")
//    public RedissonClient redisson() throws IOException {
//        Config config = new Config();
//        String prefix = "redis://";
//        config.useSingleServer()
//                .setClientName("redission")
//                .setAddress(prefix + host + ":" + port)
//                .setConnectTimeout(timeout)
//                .setDatabase(database)
//                .setPassword(password);
//        return Redisson.create(config);
//    }
//
//}
