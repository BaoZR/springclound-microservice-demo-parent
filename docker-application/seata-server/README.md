- [1. 目录介绍](#1-----)
- [2. 新建数据库表`seata`](#2--------seata-)
    * [3. 配置推送到`nacos配置中心](#3-------nacos----)
    * [3.1 对文件`config-center/config.txt`中的`store`部分进行配置，其他配置默认即可](#31-----config-center-configtxt----store----------------)
    * [3.2 执行脚本](#32-----)
- [4. docker部署seata-server](#4-docker--seata-server)
    * [4.1 修改配置文件b](#41-------b)
    * [4.2 docker方式启动`seata-server`](#42-docker-----seata-server-)
    * [4.3 检查服务](#43-----)



## 1. 目录介绍

* **`client`**:  客户端数据配置或DB文件；
* **`server`**： `seata server` DB文件；
* **`config`**:   `docker-compose`需要用的配置文件
* **`config-center`**： 用`nacos-config.py` 或者`nacos-config.sh`脚本把配置文件**`config.txt`**： 中的配置推送到nacos配置中心



## 2. 新建数据库表`seata`

> 测试发现目前`seata server`不支持8.0的数据库，只能使用mysql-5.x ，测试使用的是版本是5.7



* at模式下执行的sql 文件路径：`client/at-db`
* server db 存储文件路径：`server/db/mysql`

```

create database seata;

# at模式下执行的sql
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME     NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME     NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
  


# server db 模式下存储的数据表
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_gmt_modified_status` (`gmt_modified`, `status`),
    KEY `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- the table to store BranchSession data
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME(6),
    `gmt_modified`      DATETIME(6),
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- the table to store lock data
CREATE TABLE IF NOT EXISTS `lock_table`
(
    `row_key`        VARCHAR(128) NOT NULL,
    `xid`            VARCHAR(96),
    `transaction_id` BIGINT,
    `branch_id`      BIGINT       NOT NULL,
    `resource_id`    VARCHAR(256),
    `table_name`     VARCHAR(32),
    `pk`             VARCHAR(36),
    `gmt_create`     DATETIME,
    `gmt_modified`   DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_branch_id` (`branch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

```



### 3. 配置推送到`nacos配置中心

### 3.1 对文件`config-center/config.txt`中的`store`部分进行配置，其他配置默认即可

```
store.mode=db
store.db.dbType=mysql
store.db.datasource=druid
store.db.driverClassName=com.mysql.jdbc.Driver
store.db.url=jdbc:mysql://192.168.101.60:3306/seata?useUnicode=true
store.db.user=cjl
store.db.password=cjl1234
store.db.minConn=1
store.db.maxConn=3
store.db.globalTable=global_table
store.db.branchTable=branch_table
store.db.queryLimit=100
store.db.lockTable=lock_table
```

### 3.2 执行脚本

> * 任意执行一个
>* 参数介绍:
>
> ```
> 
> * -h: host, nacos的服务ip地址
> * -p: 端口号，nacos为 8848
> * -g: 配置组，'SEATA_GROUP'
> * -t:  对于nacos来说，就是命名空间
> 
> 脚本执行完毕，进入nacos配置中心查看，是否导入成功，如下图所示，表示导入成功，
> ```
> * 注意： 如果密码有特殊字符的时候，通过脚本配置，可能有截断，导致密码不全，启动seata-server自然报错，建议登录nacos
    >   查看配置中心如下字段是否正确，主要是`store.db.password`是否正确，nacos支持模糊查询，如`store.db.*`；



* shell脚本

  > 进入`seata-server/config-center/nacos`下执行,由于我的nacos环境是`http://192.168.101.60/`

  ```
  # 如果有命名空间，指定空间，没有不需要指定
  # sh nacos-config.sh -h 127.0.0.1 -p 8848 -g SEATA_GROUP -t 82632dc2-fce3-4288-b49e-9d270643df74
  sh nacos-config.sh -h 192.168.101.60 -p 8848 -g SEATA_GROUP
  ```

* Python 脚本

```
python nacos-config.py localhost:8848
```



> * 执行完上面的脚本，去nacos配置中心，检查，会有如下配置;
>
> * 注意： 如果密码有特殊字符的时候，通过脚本配置，可能有截断，导致密码不全，启动seata-server自然报错，建议登录nacos
    >   查看配置中心如下字段是否正确，主要是`store.db.password`是否正确，nacos支持模糊查询，如`store.db.*`；
>
>

![](https://gitee.com/mixbe/blog-image/raw/master/img/WX20210705-111210@2x.png)





## 4. docker部署seata-server

### 4.1 修改配置文件b

> `config/registry.conf`

* 注册中心`type`： nacos
* serverAddr: 配置中心ip
* group： `SEATA_GROUP`



### 4.2 docker方式启动`seata-server`

把配置好的文件拷贝到服务器某个目录，比如如下:

```
seata-server/
├── config
│   └── registry.conf
├── docker-compose.yml
```

执行docker启动命令：

```
docker-compose up -d
```

### 4.3 检查服务

进入到`nacos`服务注册中心检查是否成功注册，如果注册成功，那么恭喜💐，基础服务搭建好了

![](https://gitee.com/mixbe/blog-image/raw/master/img/WX20210705-112856@2x.png)


