package cn.demo.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description:
 * @Author: 义云
 * @Created: 2020-04-11 14:52
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.demo")
@EnableFeignClients(basePackages = "cn.demo.storage")
@MapperScan("cn.demo.storage.dao")
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
//    @Bean
//    public SentinelResourceAspect sentinelResourceAspect() {
//        return new SentinelResourceAspect();
//    }
}