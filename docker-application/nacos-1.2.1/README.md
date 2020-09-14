

## nacos-nacos 
[参考](https://github.com/nacos-group/nacos-docker)

## 步骤
1. 下载
```
git clone https://github.com/nacos-group/nacos-docker.git
```

2.  进入nacos目录
```
cd nacos-docker
```

3. 切换分支并修改版本号
 * 切换分支：`git checkout -b 1.2.1 origin/1.2.1`
 * 修改文件 `example/standalone-derby.yaml` 中的`nacos-server:latest`为`nacos-server:1.2.1`

4. 单机模式 Derby 启动
```
docker-compose -f example/standalone-derby.yaml up -d
```
5. 访问`http://127.0.0.1:8848/nacos/`
 * 用户名，密码都是`nacos`

