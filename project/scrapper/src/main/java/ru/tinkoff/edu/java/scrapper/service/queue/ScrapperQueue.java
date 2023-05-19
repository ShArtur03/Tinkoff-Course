package ru.tinkoff.edu.java.scrapper.service.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import ru.tinkoff.edu.java.scrapper.DTO.clients.bot.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@Slf4j
public class ScrapperQueue {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public ScrapperQueue(RabbitTemplate rabbitTemplate, ApplicationConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = config.getRabbitQueue().getExchangeName();
        this.routingKey = config.getRabbitQueue().getRoutingKey();
    }

    public void sendUpdate(LinkUpdateRequest update) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, update);
    }
}
