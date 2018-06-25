#!/bin/bash
cp /app/appdata/*.jar /app/app/*.jar
export ACTIVE="dev"

if [ "$APP_FLAG" == "APP"  ];then
    java -Xms2048m -Xmx2048m \
         -XX:PermSize=512M -XX:MaxNewSize=768m -XX:MaxPermSize=512m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=/app/logs/ \
		 -Dserver.port=${SERVER_PORT} \
		 -Dspring.boot.admin.url=${ADMIN_URL} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
		 -Dspring.datasource.url=${DB_URL} \
		 -Dspring.datasource.username=${DB_USERNAME} \
		 -Dspring.datasource.password=${DB_PASSWORD} \
         -Dkafka.bootstrap.servers=${KAFKA_SERVERS} \
         -Dspring.redis.cluster.nodes=${REDIS_SERVERS} \
		 -Dspring.redis.password=${REDIS_PASSWORD} \
         -jar /app/app/*.jar
         
elif [ "$APP_FLAG" == "ADMIN"  ];then
    java -Xms2048m -Xmx2048m \
         -XX:PermSize=512M -XX:MaxNewSize=768m -XX:MaxPermSize=512m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=/app/logs/ \
		 -Dserver.port=${SERVER_PORT} \
		 -Dspring.boot.admin.url=${ADMIN_URL} \
         -jar /app/app/*.jar
         
elif [ "$APP_FLAG" == "EUREKA"  ];then
    java -Xms2048m -Xmx2048m \
         -XX:PermSize=512M -XX:MaxNewSize=768m -XX:MaxPermSize=512m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=/app/logs/ \
		 -Dserver.port=${SERVER_PORT} \
		 -Dspring.boot.admin.url=${ADMIN_URL} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
         -jar /app/app/*.jar
         
elif [ "$APP_FLAG" == "GATEWAY"  ];then
    java -Xms2048m -Xmx2048m \
         -XX:PermSize=512M -XX:MaxNewSize=768m -XX:MaxPermSize=512m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=/app/logs/ \
		 -Dserver.port=${SERVER_PORT} \
		 -Dspring.boot.admin.url=${ADMIN_URL} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
         -jar /app/app/*.jar
         
else
    java -Xms2048m -Xmx2048m \
         -XX:PermSize=512M -XX:MaxNewSize=768m -XX:MaxPermSize=512m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=${LOG_PATH} \
		 -Dserver.port=${SERVER_PORT} \
		 -Dspring.boot.admin.url=${ADMIN_URL} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
		 -Dspring.datasource.url=${DB_URL} \
		 -Dspring.datasource.username=${DB_USERNAME} \
		 -Dspring.datasource.password=${DB_PASSWORD} \
         -Dkafka.bootstrap.servers=${KAFKA_SERVERS} \
         -Dspring.redis.cluster.nodes=${REDIS_SERVERS} \
		 -Dspring.redis.password=${REDIS_PASSWORD} \
         -jar /app/app/*.jar
fi