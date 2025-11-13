FROM gradle:8.10-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test


FROM eclipse-temurin:17-jdk-jammy
ENV TZ=Asia/Seoul
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y tzdata && \
    ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && \
    echo "Asia/Seoul" > /etc/timezone && \
    dpkg-reconfigure -f noninteractive tzdata && \
    apt-get clean

WORKDIR /app
ARG JAR_FILE=build/libs/server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar","/app.jar"]
