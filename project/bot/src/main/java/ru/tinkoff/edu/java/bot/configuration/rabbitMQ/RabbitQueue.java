package ru.tinkoff.edu.java.bot.configuration.rabbitMQ;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class RabbitQueue {
    @NotBlank
    private String queueName;
    @NotBlank
    private String exchangeName;
    @NotBlank
    private String routingKey;
}