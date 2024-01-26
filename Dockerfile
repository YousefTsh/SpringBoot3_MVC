FROM openjdk
LABEL authors="Yousef Taher"
RUN mkdir -p /app/mvc
WORKDIR /app/mvc
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mvc.jar
COPY src/main/resources/application.yml /app/mvc/application.yml
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/mvc/mvc.jar"]