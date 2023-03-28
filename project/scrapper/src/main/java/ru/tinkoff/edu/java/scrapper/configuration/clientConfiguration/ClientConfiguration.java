package ru.tinkoff.edu.java.scrapper.configuration.clientConfiguration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import ru.tinkoff.edu.java.scrapper.DTO.implClasses.GHClientImpl;
import ru.tinkoff.edu.java.scrapper.DTO.implClasses.StackClientImpl;

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
    public GitHubClient GHClient(@NotNull @Value("${web.url.base.github}")  String baseURL, WebClient webClient) {
        return new GHClientImpl(baseURL, webClient);
    }
    @Bean
    public StackOverFlowClient StackClient(@NotNull @Value("${web.url.base.stackoverflow}")  String baseURL, WebClient webClient) {
        return new StackClientImpl(baseURL, webClient);
    }
}
