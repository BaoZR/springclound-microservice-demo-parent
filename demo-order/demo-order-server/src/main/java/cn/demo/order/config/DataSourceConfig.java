//package cn.demo.order.config;
//
//import io.seata.rm.datasource.DataSourceProxy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * Created by IntelliJ IDEA.
// *
// * @Description:
// * @Author: 义云
// * @Created: 2020/4/19 08:49
// */
//@Configuration
//public class DataSourceConfig {
//    @Primary
//    @Bean("dataSource")
//    public DataSourceProxy dataSource(DataSource druidDataSource) {
//        return new DataSourceProxy(druidDataSource);
//    }
//}
