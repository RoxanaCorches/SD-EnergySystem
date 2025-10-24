FROM maven:3.9 AS build

ENV TZ=UTC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV CATALINA_OPTS="-Duser.timezone=UTC"

COPY ./pom.xml /auth/pom.xml
WORKDIR /auth
RUN mvn dependency:go-offline
COPY ./src/ /auth/src
WORKDIR /auth
RUN mvn clean install -DskipTests

FROM openjdk:25-jdk
WORKDIR /auth
COPY --from=build /auth/target/auth-0.0.1-SNAPSHOT.jar /auth/auth.jar
EXPOSE 8082
CMD ["java", "-jar", "auth.jar"]
