# Sring cloud 项目CI - Jenkins

### 目的：
使用 Jenkins 持续集成构建 Spring cloud 项目,并打包成 docker 项目推送 docker 仓库

### 过程：
spring cloud jenkins 持续集成在前面的文档 GitHub 集成 Jenkins 已经做好了，现在主要是如何将项目打包成docker镜像并推送  的问题，在这里我们采用的 io.fabric8 的 docker-maven-plugin
其 pom.xml 的配置如下
```
<plugin>
                <groupId>io.fabric8</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>0.30.0</version>
                <executions>
                    <execution>
                        <id>docker-build-push</id>
                        <goals>
                            <goal>build</goal>
                            <goal>push</goal>
                        </goals>
                        <phase>package</phase>
                    </execution>
                </executions>

                <configuration>
                    <pushRegistry>${docker.push.registry}</pushRegistry>
                    <images>
                        <image>
                            <alias>service</alias>
                            <name>${docker.push.registry}/${project.name}</name>
                            <build>
                                <from>java:8</from>
                                <tags>
                                    <tag>latest</tag>
                                    <tag>${project.version}</tag>
                                </tags>
                                <ports>
                                    <port>8888</port>
                                </ports>
                                <entryPoint>java -jar /app/${project.build.finalName}.jar</entryPoint>
                                <assembly>
                                    <name>${project.build.finalName}/${project.build.finalName}.jar</name>
                                    <mode>dir</mode>
                                    <targetDir>/app</targetDir>
                                    <descriptorRef>artifact</descriptorRef>
<!--                                    <descriptorRef>artifact-with-dependencies</descriptorRef>-->
                                </assembly>
                            </build>
                        </image>
                    </images>
                    <authConfig>
                        <push>
                            <username>admin</username>
                            <password>{RruZvZkg3/MH9dE418pZ8VJjh6E/q/yR4y3znZIHRxc=}</password>
                        </push>
                    </authConfig>
                </configuration>
```
#### 说明
在 ```<goals>``` 标签中我们配置了 docker 的目的是 build 和 push ，阶段是在 maven 的 package 阶段。只要 maven package 成功，接下来就能自动 build 和 push镜像.

镜像推送的是私有远程仓库，这里面在```<pushRegistry>``` 标签中设置了远程仓库的地址，在 ```<autoConfig>``` 中设置了私有仓库的用户和密码，密码采用的是maven 生成的密钥对，需要在 jenkins 所在的服务器上存储密钥，这样才能推送成功。