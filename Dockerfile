FROM openjdk:17
COPY . /app
WORKDIR /app
RUN ./mvnw package
ENTRYPOINT ["java", "-jar", "target/Trasha-0.0.1-SNAPSHOT.jar"]
