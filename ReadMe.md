#### lemon

lemon，高品质生活的人，闲着没事的时候，即使喝白开，也喜欢切一片柠檬，增添一份惬意。本项目取名lemon，也希望编写这个项目带着一份惬意去编写。

#### 前后端分离
[前端项目地址](https://github.com/ATSJP/lemon-front)

#### Project Structure

- lemon-admin   后台管理
- lemon-apply   应用
- lemon-biz-all 基础持久
- lemon-frame  常用工具
- lemon-pay     支付对接技术
- lemon-soa     微服务
    - eureka-api   服务接口定义
    - eureka-consumer 服务消费者示例（实际不适用，仅供框架搭建测试）
    - eureka-provider 服务提供者
    - eureka-server 服务注册中心
- lemon-user    前台应用

#### main dependency version

| dependency  | version          |
| ----------- | ---------------- |
| SpringBoot  | 2.0.0.RELEASE    |                         
| SpringCloud | Finchley.RELEASE |
         
#### Nginx配置

[nginx.conf]( https://github.com/ATSJP/lemon/blob/master/nginx.conf)                         

#### 技术栈

##### 后端

- SpringCloud SpringBoot 
- 安全认证框架 基于Shiro实现无状态 （SSO待实现）
- 搜索 Lucene 中文分词器 IK
- 缓存 redis client使用Redisson
- 数据库 mysql
- 连接池 druid
- 持久层 Spring Data
- 支付 ali沙箱环境
- Json fastJson
- 工具包 lombok、guava、apache common、urlRewrite

##### 前端

一期前端模仿B站，如果将来有好的项目进行重构。

- Jquery
- Layer
- CkPlayer