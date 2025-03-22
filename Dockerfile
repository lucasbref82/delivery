FROM openjdk:17-slim
WORKDIR /app

COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src /app/src



ENTRYPOINT ["top", "-b"]