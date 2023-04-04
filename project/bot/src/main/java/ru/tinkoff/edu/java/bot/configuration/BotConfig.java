package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.telegram.bot.Telegram_bot;
import ru.tinkoff.edu.java.bot.telegram.processor.Processor;
import ru.tinkoff.edu.java.bot.telegram.processor.UserMessageProcessor;

@Component
public class BotConfig {

    private UserMessageProcessor userMessageProcessor;

    public BotConfig(Processor userMessageProcessor) {
        this.userMessageProcessor = userMessageProcessor;
    }

    @Bean
    public Telegram_bot Telegram_bot(ApplicationConfig applicationConfig){
        return new Telegram_bot(applicationConfig.token(), userMessageProcessor);
    }
}