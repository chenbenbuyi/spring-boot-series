# 添加 Java 8 镜像来源
FROM java:8

# 添加参数
ARG JAR_FILE

# 添加 Spring Boot 包
ADD target/${JAR_FILE} ${JAR_FILE}

# 执行启动命令
ENTRYPOINT ["java","-jar","docker.jar"]

# 本文件仅是引子，具体Dockerfile的编写需根据业务打包镜像需求编写，才能构建起一个完整功能的镜像