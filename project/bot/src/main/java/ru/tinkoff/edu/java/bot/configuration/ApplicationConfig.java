package ru.tinkoff.edu.java.bot.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.annotation.Validated;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;
import ru.tinkoff.edu.java.LinkParser.ParserConfig;

@Validated
@Data
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
@Import({TelegramBotStarterConfiguration.class, ParserConfig.class})
public class ApplicationConfig {
    @NotNull
    private Bot bot;
    @NotNull
    private Scrapper scrapper;
    @NotNull
    private RabbitQueue rabbitQueue;

    @Validated
    @Data
    public static class Bot {
        @NotBlank
        private String name;
        @NotBlank
        private String token;
    }

    @Validated
    @Data
    public static class Scrapper{
        @NotBlank
        private String url;
    }
    @Validated
    @Data
    public static class RabbitQueue {
        @NotBlank
        private String queueName;
        @NotBlank
        private String exchangeName;
        @NotBlank
        private String routingKey;
    }
}
