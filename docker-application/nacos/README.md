
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

2. 添加环境变量
> 根据安装的版本，指定版本号, 详细看下`example/standalone-derby.yaml`文件，就知道为什么要这样做了；

```
export NACOS_VERSION=1.4.2
```

3. 单机模式Derby启动

```
docker-compose -f example/standalone-derby.yaml up -d
```

4. 访问`http://127.0.0.1:8848/nacos/`
* 用户名，密码都是`nacos`

