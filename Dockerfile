FROM maven:3.8.6-openjdk-8

ARG ARG_PROFILES

ENV SPRING_PROFILES_ACTIVE prod

ENV APP_ID spring-blog
ENV APP_DIR /millky/service/${APP_ID}

WORKDIR $APP_DIR
COPY . $APP_DIR
RUN ["mvn", "clean", "package", "-DskipTests=true"]

ENV JAVA_DEFAULT_CFG -jar -server -Dsun.net.inetaddr.ttl=0 -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -Duser.timezone=GMT+09:00
ENV JAVA_MEM -Xms1G -Xmx2G

EXPOSE 8080

CMD java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} ${JAVA_DEFAULT_CFG} ${JAVA_MEM} target/${APP_ID}.jar

## docker build -t spring-blog .
## docker tag spring-blog:latest 192.168.50.100:5050/spring-blog
## docker push 192.168.50.100:5050/spring-blog