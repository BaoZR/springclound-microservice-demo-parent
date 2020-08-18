[TOC]




## 0、 目的
> 1、微服务脚手架工程，涉及技术组件：微服务注册与发现，分布式事务，分布式锁，负载均衡， 服务熔断，服务监控，连接池等
> 2、所有的组件统一按官方推荐版本搭建，避免不必要的版本问题



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





