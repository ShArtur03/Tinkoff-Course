package ru.tinkoff.edu.java.bot.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.scrapper.Clients.LinksClient;
import ru.tinkoff.edu.java.bot.scrapper.Clients.TelegramClient;

@Configuration
public class ClientConfig {
    @Bean
    public TelegramClient telegramClient() {
        return new TelegramClient();
    }

    @Bean
    public LinksClient linksClient() {
        return new LinksClient();
    }
}
