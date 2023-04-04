package ru.tinkoff.edu.java.bot.configuration;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@ConfigurationProperties(prefix = "bot", ignoreUnknownFields = false)
public class ApplicationConfig {
    @NotNull String name;
    @NotNull String token;
}
