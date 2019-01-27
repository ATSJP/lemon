#### lemon

lemon，高品质生活的人，闲着没事的时候，即使喝白开，也喜欢切一片柠檬，增添一份惬意。本项目取名lemon，也希望编写这个项目带着一份惬意去编写。

#### Project Structure

- lemon-admin   后台管理
- lemon-apply   应用
- lemon-biz-all 基础持久
- lemon-common  常用工具
- lemon-pay     支付对接技术
- lemon-static  静态资源
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