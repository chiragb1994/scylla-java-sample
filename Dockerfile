FROM gradle:7.4.0-jdk11 as BUILD

COPY . /home/gradle/project/

ARG environment

RUN cd /home/gradle/project/ && ./gradlew clean build -i

FROM openjdk:adoptjdk-11

EXPOSE 8080

COPY --from=BUILD /home/gradle/project/build/libs/scylla-java-sample-1.0-SNAPSHOT.jar /app.jar

ENTRYPOINT exec java -jar $JAVA_OPTIONS /app.jar
