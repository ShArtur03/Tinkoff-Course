package ru.tinkoff.edu.java.bot.scrapper.Clients;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

public class TelegramClient {

    private final WebClient webClient;

    public TelegramClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public TelegramClient() {
        this.webClient = WebClient.create("http://localhost:8080/tg-chat");
    }

    public HttpStatus register(long chatId) {
        return webClient.post()
                .uri("/{id}", chatId)
                .retrieve()
                .bodyToMono(HttpStatus.class)
                .block();
    }

    public HttpStatus deleteChat(long chatId) {
        return webClient.delete()
                .uri("/{id}", chatId)
                .retrieve()
                .bodyToMono(HttpStatus.class)
                .block();
    }
}
