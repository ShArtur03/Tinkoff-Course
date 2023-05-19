package ru.tinkoff.edu.java.bot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.bot.clients.ScrapperWebClient;

@Configuration
public class WebClientConfig {
    private final ExchangeStrategies exchangeStrategies;

    public WebClientConfig(ObjectMapper objectMapper) {
        exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientCodecConfigurer -> {
                    clientCodecConfigurer.defaultCodecs()
                            .jackson2JsonEncoder(
                                    new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON)
                            );
                    clientCodecConfigurer.defaultCodecs()
                            .jackson2JsonDecoder(
                                    new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON)
                            );
                }).build();
    }

    @Bean
    public ScrapperWebClient webClient(ApplicationConfig config) {
        WebClient webClient = WebClient.builder().exchangeStrategies(exchangeStrategies)
                .baseUrl(config.getScrapper().getUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(ScrapperWebClient.class);
    }
}
