package ru.tinkoff.edu.java.bot.configuration.rabbitMQ;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.tinkoff.edu.java.bot.DTO.controller.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;


@Slf4j
@RabbitListener(queues = "${app.rabbit-queue.queue-name}")
@RequiredArgsConstructor
public class QueueListener {

    private final Bot bot;
    @RabbitHandler
    public void receive(LinkUpdateRequest message) {
        log.info(message.toString());
        bot.sendUpdates(message);
    }
}