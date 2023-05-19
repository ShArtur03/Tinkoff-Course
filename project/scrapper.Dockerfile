FROM openjdk:17
WORKDIR /application/scrapper
COPY scrapper/target/scrapper-1.0-SNAPSHOT.jar Scrapper.jar
ENTRYPOINT ["java", "-jar", "Scrapper.jar"]