[TOC]




## 0、 目的
> 1、微服务脚手架工程，涉及技术组件：微服务注册与发现，分布式事务，分布式锁，负载均衡， 服务熔断，服务监控，连接池等
> 2、所有的组件统一按官方推荐版本搭建，避免版本冲突问题
> 3. 可直接用于线上
> 4. 支持国际化项目



## 1、 架构选型

>  Spring Cloud Alibaba & Spring Cloud & Spring Boot compatibility table
>
> 严格安装 [官网](https://spring.io/projects/spring-cloud-alibaba) 推荐版本构建



### 1.1 `demo-microservice-parent` 版本历史


1. 分支- `release-1.0.0`

| Spring Cloud Version   | Spring Cloud Alibaba Version | Spring Boot Version |
| ---------------------- | ---------------------------- | ------------------- |
| Spring Cloud Hoxton.SR1 | 2.1.x.RELEASE                | 2.1.x.RELEASE       |

2. 分支- `master`

| Spring Cloud Version   | Spring Cloud Alibaba Version | Spring Boot Version |
| ---------------------- | ---------------------------- | ------------------- |
| Spring Cloud Hoxton.SR3 | 2.2.1.RELEASE        |2.2.5.RELEASE     |


3. 版本依赖
## 毕业版本依赖关系(推荐使用)

| Spring Cloud Version        | Spring Cloud Alibaba Version      | Spring Boot Version |
| --------------------------- | --------------------------------- | ------------------- |
| Spring Cloud 2020.0.0       | 2021.1                            | 2.4.2               |
| Spring Cloud Hoxton.SR9     | 2.2.6.RC1                         | 2.3.2.RELEASE       |
| Spring Cloud Greenwich.SR6  | 2.1.4.RELEASE                     | 2.1.13.RELEASE      |
| Spring Cloud Hoxton.SR3     | 2.2.1.RELEASE                     | 2.2.5.RELEASE       |
| Spring Cloud Hoxton.RELEASE | 2.2.0.RELEASE                     | 2.2.X.RELEASE       |
| Spring Cloud Greenwich      | 2.1.2.RELEASE                     | 2.1.X.RELEASE       |
| Spring Cloud Finchley       | 2.0.4.RELEASE(停止维护，建议升级) | 2.0.X.RELEASE       |
| Spring Cloud Edgware        | 1.5.1.RELEASE(停止维护，建议升级) | 1.5.X.RELEASE       |





### 1.1 官网文档

* 1、[文档](https://spring.io/projects/spring-cloud-alibaba#learn)
* 2、 [示例代码](https://spring.io/projects/spring-cloud-alibaba#samples)

  

## 2、技术组件

| 组件类别                | 组件                             |                       备注                        |
| ----------------------- | -------------------------------- | :-----------------------------------------------: |
| API网关                 | spring cloud gateway             |  https://spring.io/projects/spring-cloud-gateway  |
| 服务调用                | OpenFeign                        | https://spring.io/projects/spring-cloud-openfeign |
| 消息队列                | rocketmq                         |           https://rocketmq.apache.org/            |
| 任务调度                | xxl-job                          |         https://www.xuxueli.com/xxl-job/          |
| 注册中心                | nacos                            |              https://nacos.io/zh-cn/              |
| 配置中心                | nacos                            |              https://nacos.io/zh-cn/              |
| 微服务链路监控          | SkyWalking                       |         http://skywalking.apache.org/zh/          |
| web容器                 | undertow                         |                     websocket                     |
| web框架                 | spring boot                      |                                                   |
| ORM框架                 | mybatis                          |           http://mybatis.org/spring/zh/           |
| 数据库连接池            | druid                            |         https://github.com/alibaba/druid          |
| 数据库中间件 (读写分离) | mycat                            |                 http://mycat.io/                  |
| 分布式事务              | seata                            |          https://github.com/seata/seata           |
| 分布式锁                | redisson                         |               https://redisson.org/               |
| 分布式缓存              | Redis                            |               http://www.redis.cn/                |
| 数据库                  | Mysql                            |                       8.0.x                       |
| 内存队列                | disruptor                        |    https://github.com/LMAX-Exchange/disruptor     |
| 搜索引擎                | Elasticsearch                    |            https://www.elastic.co/cn/             |
| 工具类                  | Guava, maven                     |                                                   |
| 容器服务                | Docker, Kubernetes               |                                                   |
| 开发工具                | git,Maven,Jenkins, lombok, junit |                                                   |
| 序列化工具              | jackson                          |       https://github.com/FasterXML/jackson        |
| 参数校验                | Validator + BindResult           |                                                   |



## 3、基础服务搭建

### 3.1、 mysql-5.7搭建

[mysql-5.7搭建步骤](https://github.com/mixbe/springclound-microservice-demo-parent/tree/master/docker-application/mysql-5.7-docker)

### 3.2、mysql-8.0.18搭建

[mysql-8.0.18搭建步骤](https://github.com/mixbe/springclound-microservice-demo-parent/tree/master/docker-application/mysql-8.0-docker)

### 3.3 、nacos服务搭建

[nacos服务搭建步骤](https://github.com/mixbe/springclound-microservice-demo-parent/tree/master/docker-application/nacos-1.2.1)

## 3.4 redis服务搭建

[redis服务搭建步骤](https://github.com/mixbe/springclound-microservice-demo-parent/tree/master/docker-application/redis)

### 3.5 、seata-server 服务搭建

[seata-server服务搭建步骤](https://github.com/mixbe/springclound-microservice-demo-parent/tree/master/docker-application/seata-server-1.1.0)



## 模块介绍







## 参考
《阿里巴巴开发手册》(嵩山版)
 [Github seata](https://github.com/seata/seata)
 [seata-samples](https://github.com/seata/seata-samples)
 [setat](https://nacos.io/zh-cn/)
 [Nacos config](https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config)
 [版本说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)

