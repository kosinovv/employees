FROM openjdk:17 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/employees*.jar /usr/local/lib/employees.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/employees.jar"]