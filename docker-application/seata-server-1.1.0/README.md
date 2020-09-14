# Script Description

## client 

> 存放用于客户端的配置和SQL

- at: AT模式下的 `undo_log` 建表语句
- conf: 客户端的配置文件
- saga: SAGA 模式下所需表的建表语句
- spring: SpringBoot 应用支持的配置文件

## server

> 存放server侧所需SQL

- db: server 侧的保存模式为 `db` 时所需表的建表语句

## config-center

> 用于存放各种配置中心的初始化脚本，执行时都会读取 `config.txt`配置文件，并写入配置中心

- nacos: 用于向 Nacos 中添加配置
- zk: 用于向 Zookeeper 中添加配置，脚本依赖 Zookeeper 的相关脚本，需要手动下载；ZooKeeper相关的配置可以写在 `zk-params.txt` 中，也可以在执行的时候输入
- apollo: 向 Apollo 中添加配置，Apollo 的地址端口等可以写在 `apollo-params.txt`，也可以在执行的时候输入
- etcd3: 用于向 Etcd3 中添加配置
- consul: 用于向 consul 中添加配置


# docker部署seata-server
> 通过nacos的配置中心， 注册中心来完成

## 1. 进入`/config/registry.conf`， 修改配置文件
 > 主要配置nacos, seata 向nacos注册

## 2. 进入 `/server/db`, 创建对应的mysql数据库表
> 测试发现，docker部署seata-server，目前还不支持8.0.X的数据库，目前主要主持5.7
> 本地测试，用docker部署，选择的是5.7的版本的数据库
> 创建过程的，user, password, db 会在下一步用到；

## 3. 进入`/config-center` ,修改 config.txt 文件

> 主要配置如下字段：

* store.db.datasource
* store.db.dbType
* store.db.driverClassName
* store.db.url
* store.db.user
* store.db.password


## 4. 进入`/config-center/nacos`, 执行脚本，向nacos注册配置

* 方式1： 执行shell脚本

```$bash
sh nacos-config.sh -h 47.111.244.5 -p 8848 -g SEATA_GROUP -t 82632dc2-fce3-4288-b49e-9d270643df74
```
参数介绍:

-h: host, nacos的服务ip地址

-p: 端口号，nacos为 8848.

-g: 配置组，'SEATA_GROUP'.

-t:  对于nacos来说，就是命名空间

* 方式2： 执行python脚本

```$bash
python nacos-config.py localhost:8848
```

### 5. 配置检查，登录nacos，检查如下几个参数配置是否正确
> 注意： 如果密码有特殊字符的时候，通过脚本配置，可能有截断，导致密码不全，启动seata-server自然报错，建议登录nacos
> 查看配置中心如下字段是否正确，主要是`store.db.password`是否正确，nacos支持模糊查询，如`store.db.*`；

* store.db.datasource
* store.db.dbType
* store.db.driverClassName
* store.db.url
* store.db.user
* store.db.password


#### 参考
[配置脚本](https://github.com/seata/seata/tree/develop/script)