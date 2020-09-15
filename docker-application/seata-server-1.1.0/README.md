[TOC]

# 1. client目录

> 存放用于客户端的配置和SQL

- at: AT模式下的 `undo_log` 建表语句
- conf: 客户端的配置文件
- saga: SAGA 模式下所需表的建表语句
- spring: SpringBoot 应用支持的配置文件

> demo主要使用AT模式,所以，只需要执行at目录下的sql，（目前不支持最新的mysql8.0的版本，所以只能用搭建的mysql5.7来测试）

* 创建数据库`seata`和表`undo_log`, 等会配置文件需要使用
```$xslt
➜  mysql-5.7 mysql -h127.0.0.1  -uadmin -P3337 -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 70
Server version: 5.7.31 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.03 sec)

mysql> create database seata;
Query OK, 1 row affected (0.01 sec)

mysql> use seata;
Database changed
mysql> CREATE TABLE IF NOT EXISTS `undo_log`
    -> (
    ->     `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    ->     `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    ->     `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    ->     `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    ->     `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    ->     `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    ->     `log_created`   DATETIME     NOT NULL COMMENT 'create datetime',
    ->     `log_modified`  DATETIME     NOT NULL COMMENT 'modify datetime',
    ->     PRIMARY KEY (`id`),
    ->     UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
    -> ) ENGINE = InnoDB
    ->   AUTO_INCREMENT = 1
    ->   DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
Query OK, 0 rows affected (0.04 sec)

mysql> show tables;
+-----------------+
| Tables_in_seata |
+-----------------+
| undo_log        |
+-----------------+
1 row in set (0.00 sec)
```

# 2. server目录
- db: server 侧的保存模式为 `db` 时所需表的建表语句

*  存放server侧所需SQL， 按上一步的操作，继续创建3个表`global_table`,`branch_table`, `lock_table`

最终的表如下：
```$xslt
mysql> show tables;                                                                                                                                                                                                                           +-----------------+
| Tables_in_seata |
+-----------------+
| branch_table    |
| global_table    |
| lock_table      |
| undo_log        |
+-----------------+
```

# 3. config-center目录

> 用于存放各种配置中心的初始化脚本，执行时都会读取 `config.txt`配置文件，并写入配置中心

- nacos: 用于向 Nacos 中添加配置，(这个是我们domo的主要配置方式)
- zk: 用于向 Zookeeper 中添加配置，脚本依赖 Zookeeper 的相关脚本，需要手动下载；ZooKeeper相关的配置可以写在 `zk-params.txt` 中，也可以在执行的时候输入
- apollo: 向 Apollo 中添加配置，Apollo 的地址端口等可以写在 `apollo-params.txt`，也可以在执行的时候输入
- etcd3: 用于向 Etcd3 中添加配置
- consul: 用于向 consul 中添加配置


# 4. docker部署seata-server
> 通过nacos的配置中心， 注册中心来完成

## 1. 进入`/config/registry.conf`， 修改配置文件
 > 主要配置nacos, seata 向nacos注册

## 2. 进入 `/server/db`, 创建对应的mysql数据库表
> 测试发现，docker部署seata-server，目前还不支持8.0.X的数据库，目前主要主持5.7
> 本地测试，用docker部署，选择的是5.7的版本的数据库
> 创建过程的，user, password, db 会在下一步用到；

## 3. 进入`/config-center` ,修改 config.txt 文件

> 如果是DB模式，主要配置如下字段：

```$xslt
store.mode=db
store.db.dbType=mysql
store.db.datasource=druid
store.db.driverClassName=com.mysql.jdbc.Driver
store.db.url=jdbc:mysql://127.0.0.1:3337/seata?useUnicode=true
store.db.user=admin
store.db.password=admin123
```


## 4. 进入`/config-center/nacos`, 执行脚本，向nacos注册配置

> `cd config-center/nacos`, 两种方式执行一次即可

* 方式1： 执行shell脚本
```$bash
# sh nacos-config.sh -h 127.0.0.1 -p 8848 -g SEATA_GROUP -t 82632dc2-fce3-4288-b49e-9d270643df74
sh nacos-config.sh -h 127.0.0.1 -p 8848 -g SEATA_GROUP
```
参数介绍:

* -h: host, nacos的服务ip地址
* -p: 端口号，nacos为 8848
* -g: 配置组，'SEATA_GROUP'
* -t:  对于nacos来说，就是命名空间
* 方式2： 执行python脚本

脚本执行完毕，进入nacos配置中心查看，是否导入成功，如下图所示，表示导入成功，

> 注意： 如果密码有特殊字符的时候，通过脚本配置，可能有截断，导致密码不全，启动seata-server自然报错，建议登录nacos
> 查看配置中心如下字段是否正确，主要是`store.db.password`是否正确，nacos支持模糊查询，如`store.db.*`；
>
![nacos配置列表.png](https://i.loli.net/2020/09/14/5IzNysY9PBwuiJE.png)

```$Python
python nacos-config.py localhost:8848
```

#


#### 参考
[配置脚本](https://github.com/seata/seata/tree/develop/script)