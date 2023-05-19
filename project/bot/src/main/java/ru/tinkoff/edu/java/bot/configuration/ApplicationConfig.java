package ru.tinkoff.edu.java.bot.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.bot.configuration.rabbitMQ.RabbitQueue;

@Validated
@ConfigurationProperties(prefix = "bot", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String name, @NotNull String token, @NotNull RabbitQueue rabbitQueue) {
}
