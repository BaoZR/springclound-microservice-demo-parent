
[参考](https://github.com/nacos-group/nacos-docker)

## 步骤
1. 拉取代码并切换分支
```
# 拉取代码
git clone https://github.com/nacos-group/nacos-docker.git

# 进入项目
cd nacos-docker

# 查看所有的远程分支
git branch -a

# 切换分支
git checkout -b 1.2.1 origin/1.2.1

```

2. 修改文件

* 打开文件: `vim example/standalone-derby.yaml`
* 修改文件: `nacos-server:latest`为`nacos-server:1.2.1`

更改后的内容：
```
version: "2"
services:
  nacos:
    image: nacos/nacos-server:1.2.1
    container_name: nacos-standalone
    environment:
    - PREFER_HOST_MODE=hostname
    - MODE=standalone
    volumes:
    - ./standalone-logs/:/home/nacos/logs
    - ./init.d/custom.properties:/home/nacos/init.d/custom.properties
    ports:
    - "8848:8848"
  prometheus:
    container_name: prometheus
    image: prom/prometheus:latest
    volumes:
      - ./prometheus/prometheus-standalone.yaml:/etc/prometheus/prometheus.yml
    ports:
      - "9090:9090"
    depends_on:
      - nacos
    restart: on-failure
  grafana:
    container_name: grafana
    image: grafana/grafana:latest
    ports:
      - 3000:3000
    restart: on-failure
```

3. 单机模式 Derby 启动
```
docker-compose -f example/standalone-derby.yaml up -d
```

4. 访问`http://127.0.0.1:8848/nacos/`
* 用户名，密码都是`nacos`

