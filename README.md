# 工程简介

本项目为简易网页版线上书店，为 SJTU SE2321 课程大作业项目后端部分。
本项目代码已合入上海交通大学软件学院代码查重库，请学弟学妹注意，不要抄袭！！！

# 项目启动
## MySQL数据库：需要配置数据库
需要依据实际情况修改 application.properties 中 spring.datasource 相关的内容

# 项目启动 SE3353 相关分支
## 消息中间件：启动该项目需要先启动 zookeeper 并开启 Kafka
### zookeeper
进入 zookeeper 安装路径 /bin 目录下，先启动 zkServer，再启动 zkCli
### Kafka
在命令行中启动 Kafka
安装目录在 D:\Kafka\kafka_2.13-3.2.1\ Windows 中命令为
D:\Kafka\kafka_2.13-3.2.1\bin\windows\kafka-server-start.bat D:\Kafka\kafka_2.13-3.2.1\config\server.properties
## 安全
需要依据实际情况修改 application.properties 中 server.ssl.key 相关的内容
## 缓存 Redis
在命令行中启动 Redis，安装目录 D:\Redis\Redis-x64-3.0.504\ 执行
D:\Redis\Redis-x64-3.0.504\redis-server.exe D:\Redis\Redis-x64-3.0.504\redis.windows.conf
## 全文搜索 solr
在命令行中启动 solr
D:\solr\solr-8.11.2\bin\solr start -e cloud
## mongoDB
需要依据实际情况修改 application.properties 中 mongodb.uri 相关的内容
##neo4j
下载并启动 neo4j
neo4j.bat console

