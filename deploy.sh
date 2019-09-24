nohup java -jar lemon-config-0.0.1-SNAPSHOT.jar > log/config.log 2>&1 &
nohup java -jar eureka-server-0.0.1-SNAPSHOT.jar > log/server.log 2>&1 &

nohup java -jar eureka-provider-0.0.1-SNAPSHOT.jar --spring.cloud.config.profile=prod > log/provider.log 2>&1 &
nohup java -jar lemon-pay-0.0.1-SNAPSHOT.jar --spring.cloud.config.profile=prod > log/pay.log 2>&1 &
nohup java -jar lemon-user-0.0.1-SNAPSHOT.jar --spring.cloud.config.profile=prod > log/user.log 2>&1 &
nohup java -jar lemon-apply-0.0.1-SNAPSHOT.jar --spring.cloud.config.profile=prod > log/apply.log 2>&1 &
