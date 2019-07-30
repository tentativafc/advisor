FROM maven:3.6.1-jdk-11-slim

WORKDIR /app

COPY . .

RUN "ls"

ENV SPRING_PROFILES_ACTIVE=prod

CMD ["mvn", "clean", "spring-boot:run"]