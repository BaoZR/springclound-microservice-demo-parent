package cn.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @version 1.0
 * @Author 义云
 * @Description
 * @Date 2020/4/16 17:17
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    /**
//     * 负载均衡
//     * @param loadBalance
//     * @return
//     */
//    @Bean
//    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
//        return new LoadBalancerInterceptor(loadBalance);
//    }
}
