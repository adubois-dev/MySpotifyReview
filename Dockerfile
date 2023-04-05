FROM openjdk:17 as builder
WORKDIR application
COPY ./pom.xml ./pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY ./src ./src
RUN ["chmod", "+x", "mvnw"]
RUN ./mvnw -N io.takari:maven:wrapper
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw clean package && cp target/statify-1.0.jar statify-1.0.jar
RUN java -Djarmode=layertools -jar statify-1.0.jar extract
#ENTRYPOINT ["java","-jar", "publish-docker-image-to-docker-hub-1.0-SNAPSHOT.jar"]
FROM openjdk:20-jre
WORKDIR application
COPY – from=builder application/dependencies/ ./
COPY – from=builder application/spring-boot-loader/ ./
COPY – from=builder application/snapshot-dependencies/ ./
COPY – from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
