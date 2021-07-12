- [0、 目的](#0、 目的)
- [1、 版本](#1----)
- [2、常用技术组件](#2-------)
- [3. 架构图](#3----)
    * [4、基础服务搭建](#4-------)
    * [4.1、 mysql-5.7搭建](#41--mysql-57--)
    * [4.2、mysql-8.0.18搭建](#42-mysql-8018--)
    * [4.3 、nacos服务搭建](#43--nacos----)
    * [.44 、seata-server 服务搭建](#44--seata-server-----)
- [5. 模块介绍](#5-----)
- [6. 项目启动](#6-----)
- [7. 项目测试](#7-----)
- [8. 参考](#8---)



微服务脚手架工程，涉及技术组件：微服务注册与发现，分布式事务，分布式锁，负载均衡， 服务熔断，服务监控，连接池等;



# 0、 目的

>  搭建一套可用的微服务环境，严格依赖官方推荐的版本，避免版本之间的依赖问题。



- [x] Nacos 注册中心
- [x] Nacos 配置中心
- [x] Open Feign REST 服务调用
- [x] Seata 分布式事务解决方案
- [ ] Sentinel 限流熔断
- [ ] Dubbo RPC 服务调用





# 1、 版本

>  Spring Cloud Alibaba & Spring Cloud & Spring Boot compatibility table
>
>  严格安装 [官方推荐版本](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E) 构建
>
>  说明： 分支`nacos_1.2.1`用的是相对老的版本，`master`分支用的是当前新的版本



* 组件关系

| Spring Cloud Alibaba Version | Sentinel Version | Nacos Version | RocketMQ Version | Dubbo Version | Seata Version |
| ---------------------------- | ---------------- | ------------- | ---------------- | ------------- | ------------- |
| 2.2.6.RELEASE                | 1.8.1            | 1.4.2         | 4.4.0            | 2.7.8         | 1.3.0         |



* 依赖关系

| Spring Cloud Version    | Spring Cloud Alibaba Version | Spring Boot Version                   |
| ----------------------- | ---------------------------- | ------------------------------------- |
| Spring Cloud Hoxton.SR9 | 2.2.6.RELEASE                | 2.3.2.RELEASE(项目用的2.3.10.RELEASE) |



# 2、常用技术组件

| 组件类别                | 组件                             |                       备注                        |
| ----------------------- | -------------------------------- | :-----------------------------------------------: |
| API网关                 | spring cloud gateway             |  https://spring.io/projects/spring-cloud-gateway  |
| 服务调用                | OpenFeign                        | https://spring.io/projects/spring-cloud-openfeign |
| 消息队列                | rocketmq                         |           https://rocketmq.apache.org/            |
| 任务调度                | xxl-job                          |         https://www.xuxueli.com/xxl-job/          |
| 注册中心                | nacos                            |              https://nacos.io/zh-cn/              |
| 配置中心                | nacos                            |              https://nacos.io/zh-cn/              |
| 微服务链路监控          | SkyWalking                       |         http://skywalking.apache.org/zh/          |
| web容器                 | undertow                         |                                                   |
| web框架                 | spring boot                      |                                                   |
| ORM框架                 | mybatis                          |           http://mybatis.org/spring/zh/           |
| 数据库连接池            | druid                            |         https://github.com/alibaba/druid          |
| 数据库中间件 (读写分离) | mycat                            |                 http://mycat.io/                  |
| 分布式事务              | seata                            |          https://github.com/seata/seata           |
| 分布式锁                | redisson                         |               https://redisson.org/               |
| 分布式缓存              | Redis                            |               http://www.redis.cn/                |
| 数据库                  | Mysql                            |                                                   |
| 内存队列                | disruptor                        |    https://github.com/LMAX-Exchange/disruptor     |
| 搜索引擎                | Elasticsearch                    |            https://www.elastic.co/cn/             |
| 工具类                  | Guava, maven                     |                                                   |
| 容器服务                | Docker, Kubernetes               |                                                   |
| 开发工具                | git,Maven,Jenkins, lombok, junit |                                                   |
| 序列化工具              | jackson                          |       https://github.com/FasterXML/jackson        |
| 参数校验                | Validator + BindResult           |                                                   |



# 3. 架构图

> 比较经典的微服务架构图😄

![WX20210712-170410@2x.png](https://i.loli.net/2021/07/12/ExsdJFIQuSMhUmR.png)




## 4、基础服务搭建

> * 下面都是用docker搭建的服务，如果已经在物理机部署成功的服务，可以忽略



## 4.1、 mysql-5.7搭建

[mysql-5.7搭建步骤](./docker-application/mysql-5.7-docker/README.md)

## 4.2、mysql-8.0.18搭建

[mysql-8.0.18搭建步骤](./docker-application/mysql-8.0-docker/README.md)

## 4.3 、nacos服务搭建

[nacos服务搭建步骤](./docker-application/nacos/README.md)

## .44 、seata-server 服务搭建

> seata版本为1.3.0,严格按照官网推荐的版本部署

[seata-server服务搭建步骤](docker-application/seata-server)



# 5. 模块介绍

| 文件目录             | 说明                            | 端口 |
| -------------------- | ------------------------------- | ---- |
| `demo-common-utils`  | 公共服务，工具类等              |      |
| `demo-gateway`       | 项目的网关                      | 4000 |
| `demo-order`         | 订单服务                        | 4001 |
| `demo-storage`       | 仓库服务                        | 4002 |
| `i18n`               | 国家化(暂时没有用到)            |      |
| `config-files`       | 项目配置文件(上传到nacos的配置) |      |
| `docker-application` | 启动docker服务的文件            |      |



# 6. 项目启动

> 启动项目之前，把`config-files`目录下配置文件放到nacos配置中心

* `Data ID`: 文件名称(包含后缀)
* `Group`： 用的默认`DEFAULT_GROUP`
* `配置格式`： `YAML`

![](https://gitee.com/mixbe/blog-image/raw/master/img/WX20210712-171348@2x.png)
![](https://gitee.com/mixbe/blog-image/raw/master/img/WX20210709-171136@2x.png)







# 7. 项目测试

* 通过`gateway`服务请求

```
curl -d '{"userId":1,"payMoney":0.2,"productId":1,"count":2}' -H "Content-Type: application/json" -X POST '127.0.0.1:40000/api/order/create'
```

* 通过`Order`服务请求
```
curl -d '{"userId":1,"payMoney":0.2,"productId":1,"count":2}' -H "Content-Type: application/json" -X POST '127.0.0.1:40001/order/create'
```


![](https://gitee.com/mixbe/blog-image/raw/master/img/WX20210712-154807@2x.png)


# 8. 参考
《阿里巴巴开发手册》(嵩山版)
[Github seata](https://github.com/seata/seata)
[seata-samples](https://github.com/seata/seata-samples)
[setat](https://nacos.io/zh-cn/)
[Nacos config](https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config)
[版本说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)


