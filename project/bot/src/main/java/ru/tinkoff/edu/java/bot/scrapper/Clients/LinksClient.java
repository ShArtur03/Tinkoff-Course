package ru.tinkoff.edu.java.bot.scrapper.Clients;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.scrapper.DTO.AddLinkRequest;
import ru.tinkoff.edu.java.bot.scrapper.DTO.LinkResponse;
import ru.tinkoff.edu.java.bot.scrapper.DTO.ListLinksResponse;
import ru.tinkoff.edu.java.bot.scrapper.DTO.RemoveLinkRequest;

@Component
public class LinksClient {

    private final WebClient webClient;

    public LinksClient(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public LinksClient() {
        this.webClient = WebClient.create("http://localhost:8080/");
    }

    public LinkResponse addLink(Long chatId, AddLinkRequest linkRequest) {
        return webClient.post()
                .header("Tg-Chat-Id", chatId.toString())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequest), AddLinkRequest.class)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public LinkResponse deleteLink(Long chatId, RemoveLinkRequest linkRequest) {
        return webClient.method(HttpMethod.DELETE)
                .uri("/links")
                .header("Tg-Chat-Id", chatId.toString())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequest), RemoveLinkRequest.class)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public ListLinksResponse getAllLinks(Long chatId) {
        return webClient.get()
                .uri("/links")
                .accept(MediaType.APPLICATION_JSON)
                .header("Tg-Chat-Id", chatId.toString())
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

}
