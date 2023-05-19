package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.DTO.requests.LinkUpdate;

import java.time.Duration;

public class BotClient {
    private String baseUrl = "http://localhost:8080/";

    private final WebClient webClient;

    public BotClient(WebClient webClient) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public BotClient(WebClient webClient,String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder()
                .baseUrl(this.baseUrl)
                .build();
    }

    public void sendUpdate(LinkUpdate request) {
        this.webClient.post()
                .uri("updates")
                .body(Mono.just(request), LinkUpdate.class)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(10))
                .block();
    }
}
