# Redis

## 一，什么是redis

redis全称：Remote Dictionary Server（远程数据服务）

### 1.1：概念

- 非关系型数据库，（常见关系型数据库，MySQL，DB2 ，Microsoft Access，Microsoft SQL Server）
- 支持分布式，理论上可以无线扩展
- key-value存储形式
- 开始的使用 ANSI C语言编写，
- 常见五种数据类型：
  - String
  - List
  - Set：无序不重复集合
  - Zset：redis特有的一种  有序不重复集合
  - Hash

### 1.2：与常见数据库的不同点

- 不需要建库，本身自带16个数据库（0-15）
- 在数据库中key是唯一存在的，不允许重复的，
- 查询是根据key进行查询
- 可以把多条数据看成一条数据进行存储
- 没有表的概念，通过不同的数据类型实现存储数据需求

## 二，redis的作用，redis的应用场景

### 2.1：常见作用：

- 作为缓存使用，可以把数据持久保存

### 2.2：应用场景：

- 热点数据
- 高频读
- 低频写

## 三，项目中如何使用redis

### 3.1：基础步骤

导入redis依赖

```java
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <version>2.6.3</version>
        </dependency>
```

配置文件

```java
redis:
    #    Redis服务器地址
    host: 127.0.0.1
    #      Redis服务器链接端
    port: 6379
    #      Redis服务器密码
    password:
    #      Redis数据库索引
    database: 3
    jedis:
      pool:
        #       连接池最大连接数（使用负值表示没有限制）
        max-active: 20
        #        连接池最大阻塞时间（使用负值表示没有限制）
        max-wait: -1
        #          最大空闲链接
        max-idle: 10
        #          最小空闲链接
        min-idle: 0

    #          链接超市时间（毫秒）
    timeout: 1000
```

### 3.2：设置redis配置类

RedisConfig配置类，--通用写法

### 3.3：使用redis（增删改查）


