FROM openjdk:17
WORKDIR /application/bot
COPY bot/target/bot-1.0-SNAPSHOT.jar TelegramBot.jar
ENTRYPOINT ["java", "-jar", "TelegramBot.jar"]