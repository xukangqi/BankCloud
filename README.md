
# 注意事项
需要在本地host中加入规则
```
127.0.0.1 eurekaserver1
127.0.0.1 eurekaserver2
127.0.0.1 eurekaserver3
```
## 容器化配置
在pom.xml文件的properties标签内需要添加
```xml
<!--name一律为bank/XXX-->
 <docker.image.name>bank/demo-consumer</docker.image.name>
 <!--tag 统一为1.0-->
  <docker.image.tag>1.0</docker.image.tag>
```
在build->plugins标签中还需要增加docker标签
```xml
            <plugin>
                <artifactId>maven-resources-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-resources</id>
                        <!-- here the phase you need -->
                        <phase>validate</phase>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${basedir}/target/dockerfile</outputDirectory>
                            <resources>
                                <resource>
                                    <directory>src/main/docker</directory>
                                    <filtering>true</filtering>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>0.4.10</version>
                <configuration>
                    <imageName>${docker.image.name}:${docker.image.tag}</imageName>
                    <dockerDirectory>${basedir}/target/dockerfile</dockerDirectory>
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>
```
## 数据库相关设置
在使用之前需要修改application.yml中的相关配置，包括用户名，密码等<br>
访问localhost:9001/test/database测试<br>
还配置有druid监控页面,通过 localhost:9001/druid访问，账号密码都是admin
