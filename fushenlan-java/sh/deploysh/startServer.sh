#!/bin/bash
cp /app/appdata/*.jar /app/app/*.jar
if [ "$APP_FLAG" == "APP"  ];then
    java -XX:+HeapDumpOnOutOfMemoryError \
         -XX:HeapDumpPath=/app/logs/java.hprof \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=${LOGGING_PATH} \
		 -Dserver.port=${SERVER_PORT} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
         -jar /app/app/*.jar
         
elif [ "$APP_FLAG" == "ADMIN"  ];then
    java -Xms4096m -Xmx4096m \
         -XX:PermSize=1024M -XX:MaxNewSize=1536m -XX:MaxPermSize=1024m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=${LOGGING_PATH} \
		 -Dserver.port=${SERVER_PORT} \
		 -Dspring.boot.admin.url=${ADMIN_URL} \
         -jar /app/app/*.jar
         
elif [ "$APP_FLAG" == "EUREKA"  ];then
    java -Xms4096m -Xmx4096m \
         -XX:PermSize=1024M -XX:MaxNewSize=1536m -XX:MaxPermSize=1024m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=${LOGGING_PATH} \
		 -Dserver.port=${SERVER_PORT} \
		 -Dspring.boot.admin.url=${ADMIN_URL} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
         -jar /app/app/*.jar
         
elif [ "$APP_FLAG" == "GATEWAY"  ];then
    java -Xms4096m -Xmx4096m \
         -XX:PermSize=1024M -XX:MaxNewSize=1536m -XX:MaxPermSize=1024m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=${LOGGING_PATH} \
		 -Dserver.port=${SERVER_PORT} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
         -jar /app/app/*.jar
         
elif [ "$APP_FLAG" == "CONFIG"  ];then
    java -Xms4096m -Xmx4096m \
         -XX:PermSize=1024M -XX:MaxNewSize=1536m -XX:MaxPermSize=1024m \
         -Dfile.encoding=UTF-8 \
		 -Dspring.logging.path=${LOGGING_PATH} \
		 -Dserver.port=${SERVER_PORT} \
		 -Dspring.boot.admin.url=${ADMIN_URL} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
         -jar /app/app/*.jar
		 
else
    java -Xms4096m -Xmx4096m \
         -XX:PermSize=1024M -XX:MaxNewSize=1536m -XX:MaxPermSize=1024m \
         -Dfile.encoding=UTF-8 \
         -Dspring.profiles.active=${ACTIVE} \
		 -Dspring.logging.path=${LOG_PATH} \
		 -Dserver.port=${SERVER_PORT} \
		 -Deureka.client.serviceUrl.defaultZone=${EUREKA_ZONE} \
         -jar /app/app/*.jar
fi