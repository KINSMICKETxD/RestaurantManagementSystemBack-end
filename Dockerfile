FROM maven:3.9.5-eclipse-temurin-17 AS build


WORKDIR /app


COPY .mvn/ .mvn

COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY . .

RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:17-jdk-focal

WORKDIR /app


COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java","-jar","app.jar"]