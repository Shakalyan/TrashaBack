FROM openjdk:17-alpine

RUN apk update \
&& apk upgrade \
&& apk add python3 \
&& apk add py3-pip \
&& pip install deep-translator

COPY . /app
WORKDIR /app
RUN ./mvnw package
ENTRYPOINT ["java", "-jar", "target/Trasha-0.0.1-SNAPSHOT.jar"]