package ru.tinkoff.edu.java.scrapper.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.scrapper.clients.BotWebClient;
import ru.tinkoff.edu.java.scrapper.clients.GitHubWebClient;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowWebClient;

@Configuration
public class WebClientConfig {

    private final ExchangeStrategies exchangeStrategies;
    private static final Integer MAX_BUFF_SIZE = 1024 * 1024 * 10;
    public WebClientConfig(ObjectMapper objectMapper) {
        exchangeStrategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer
                            .defaultCodecs()
                            .jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer
                            .defaultCodecs()
                            .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer.defaultCodecs().maxInMemorySize(MAX_BUFF_SIZE);
                }).build();
    }

    @Bean
    public GitHubWebClient gitHubWebClient(ApplicationConfig config) {
        WebClient webClient = WebClient.builder().exchangeStrategies(exchangeStrategies)
                .baseUrl(config.getGitHub().getUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(GitHubWebClient.class);
    }

    @Bean
    public StackOverflowWebClient stackOverflowWebClient(ApplicationConfig config) {
        WebClient webClient = WebClient.builder().exchangeStrategies(exchangeStrategies)
                .baseUrl(config.getStackOverflow().getUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(StackOverflowWebClient.class);
    }

    @Bean
    public BotWebClient botWebClient(ApplicationConfig config) {
        WebClient webClient = WebClient.builder().exchangeStrategies(exchangeStrategies)
                .baseUrl(config.getBot().getUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(BotWebClient.class);
    }
}
