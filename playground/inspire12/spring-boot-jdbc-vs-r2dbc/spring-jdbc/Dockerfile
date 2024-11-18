FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.5_10-slim as builder

WORKDIR /src
COPY src ./src/
COPY gradle ./gradle/
COPY build.gradle ./
COPY gradlew ./
COPY settings.gradle ./
#COPY gradle.properties ./
RUN chmod 777 ./gradlew
RUN ./gradlew clean build --exclude-task testClasses

##
#  Second Stage
##
FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.5_10-slim
EXPOSE 8080
COPY --from=builder /src/build/libs/spring-jdbc*  /app/spring-boot.jar
ENTRYPOINT ["java","-Duser.timezone=GMT+1","-jar","/app/spring-boot.jar"]