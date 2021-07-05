# 目录介绍

> docker-compose 镜像文件, 如果没有物理部署，可以使用docker部署策略，已经安装的，可以忽略

* [mysql:5.7 安装方式(可选)](./mysql-5.7-docker/README.md)
* [mysql:8.0 安装方式(可选)](./mysql-8.0-docker/)
* redis
* [nacos 安装方式](./nacos/README.md)
* [seata](./seata-server/README.md)

# 配置镜像源

> 为了更快速的下载镜像源，提供如下配置

* 如果没有新建文件(如果没有这个文件)

`sudo vim /etc/docker/daemon.json`

```
{
  "registry-mirrors": [
    "https://h2hjalj9.mirror.aliyuncs.com",
    "http://hub-mirror.c.163.com",
    "http://docker.mirrors.ustc.edu.cn"
  ]
}
```

* 重新加载(Centos系统如下命令，其他系统做下修改)

```
sudo systemctl daemon-reload
sudo systemctl restart docker.service
```




