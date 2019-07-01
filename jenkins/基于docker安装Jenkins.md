## 基于docker部署CI/CD

### 获取jenkins

```
git clone https://github.com/AliyunContainerService/docker-jenkins
cd docker-jenkins/jenkins
docker build -t myjenkins
```

首先通过git clone 获取jenkins，在jenkins文件夹中已经存在配置好的Dockerfile 文件，运行docker build命令构建相应的jenkins镜像。

通过如下命令可以查看已经构建的docker镜像

```
docker images ls
```

### 启动容器

基于构建好的镜像启动容器

```
docker run -d -p 0.0.0.0:8011:8080 -p 50000:50000 -v $(pwd)/data:/var/jenkins_home --name jenkins myjenkins
```

通过如下命令查看容器

```
docker ps
```

