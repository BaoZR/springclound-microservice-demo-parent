package cn.demo.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
@SpringBootApplication(scanBasePackages = {"cn.demo"}, exclude = DataSourceAutoConfiguration.class)
@MapperScan(value = {"cn.demo.storage.mapper"})
@EnableFeignClients(basePackages = "cn.demo.storage")
public class StorageApplication {

  public static void main(String[] args) {

    SpringApplication.run(StorageApplication.class, args);
  }

}