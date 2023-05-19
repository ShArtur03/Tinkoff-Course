package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.DTO.controller.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.configuration.rabbitMQ.QueueListener;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "rabbit-queue")
public class RabbitMQConfig {
    private final String exchangeName;
    private final String queueName;
    private final String routingKey;

    public RabbitMQConfig(ApplicationConfig config) {
        this.exchangeName = config.getRabbitQueue().getExchangeName();
        this.queueName = config.getRabbitQueue().getQueueName();
        this.routingKey = config.getRabbitQueue().getRoutingKey();
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }

    @Bean
    public ClassMapper classMapper() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("ru.tinkoff.edu.java.scrapper.DTO.clients.bot.LinkUpdateRequest", LinkUpdateRequest.class);
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.scrapper.DTO.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(exchangeName + ".dlq");
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder
                .durable(queueName + ".dlq")
                .build();
    }

    @Bean
    public Binding deadLetterbinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterExchange)
                .with(routingKey + ".dlq");
    }

    @Bean
    public QueueListener queueListener(Bot bot) {
        return new QueueListener(bot);
    }
}