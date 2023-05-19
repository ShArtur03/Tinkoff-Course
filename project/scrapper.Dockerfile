FROM openjdk:17
WORKDIR /application/scrapper
COPY scrapper/target/bot-1.0-SNAPSHOT.jar Scrapper.jar
ENTRYPOINT ["java", "-jar", "Scrapper.jar"]