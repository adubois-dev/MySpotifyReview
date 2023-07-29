FROM maven:3.8.3-openjdk-17 as builder
WORKDIR application
COPY ./pom.xml ./pom.xml
COPY .mvn .mvn
COPY ./src ./src
RUN mvn -N io.takari:maven:wrapper
RUN mvn dependency:go-offline -B
RUN mvn clean package && cp target/statify-latest.jar Statify-PROTOTYPE.jar
RUN java -Djarmode=layertools -jar Statify-PROTOTYPE.jar extract
#ENTRYPOINT ["java","-jar", "publish-docker-image-to-docker-hub-1.0-SNAPSHOT.jar"]
FROM openjdk:17-oracle
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
