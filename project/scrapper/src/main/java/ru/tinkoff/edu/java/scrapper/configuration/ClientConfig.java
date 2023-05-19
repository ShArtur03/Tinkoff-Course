package ru.tinkoff.edu.java.scrapper.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import ru.tinkoff.edu.java.scrapper.clients.*;

import java.util.concurrent.TimeUnit;

@Configuration
public class ClientConfig {

    @Bean
    public WebClient webClient() {
        var httpClient = HttpClient
                .create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 7000)
                .doOnConnected(con -> {
                    con.addHandlerLast(new ReadTimeoutHandler(7000, TimeUnit.MILLISECONDS));
                    con.addHandlerLast(new WriteTimeoutHandler(7000, TimeUnit.MILLISECONDS));
                }).compress(true);
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

    @Bean("gitHubClient")
    public GitHubClient gitHubClient(@Value("${web.url.base.github}") String baseUrl, WebClient webClient) {
        return new HttpGitHubClient(webClient, baseUrl);
    }

    @Bean("stackOverFlowClient")
    public StackOverFlowClient stackOverFlowClient(@Value("${web.url.base.stackoverflow}") String baseUrl, WebClient webClient) {
        return new HttpStackOverFlowClient(webClient, baseUrl);
    }

    @Bean("botClient")
    public BotClient botClient(WebClient webClient) {
        return new BotClient(webClient);
    }
}
