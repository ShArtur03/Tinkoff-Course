package ru.tinkoff.edu.java.bot.scrapper.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import ru.tinkoff.edu.java.bot.scrapper.Clients.ScrapperClient;
import ru.tinkoff.edu.java.bot.scrapper.Clients.ScrapperClientImpl;

import java.util.concurrent.TimeUnit;

@Configuration
public class ClientConfiguration {
    private static final int TIMEOUT = 1000;
    @Bean
    public WebClient webClient() {
        var httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT).doOnConnected(connection -> {
            connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
            connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
        });
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
    @Bean
    public ScrapperClient scrapperClient(@NotNull WebClient webClient) {
        return new ScrapperClientImpl(webClient);
    }
}
