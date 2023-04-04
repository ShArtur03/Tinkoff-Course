package ru.tinkoff.edu.java.bot.scrapper.Clients;

import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.DTO.response.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.scrapper.DTO.AddLinkRequest;
import ru.tinkoff.edu.java.bot.scrapper.DTO.LinkResponse;
import ru.tinkoff.edu.java.bot.scrapper.DTO.ListLinksResponse;
import ru.tinkoff.edu.java.bot.scrapper.exception.ApiClientErrorException;
import ru.tinkoff.edu.java.bot.scrapper.exception.ApiInternalServerErrorException;
public class ScrapperClientImpl implements ScrapperClient{

    private static final Logger LOGGER = LogManager.getLogger(ScrapperClientImpl.class);
    private final @NotNull WebClient webClient;
    private @NotNull String baseUrl = "http://localhost:8080";

    private final String tgChatUrl = "/tg-chat/{id}";
    private final String linksUrl = "/links";

    public ScrapperClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public ScrapperClientImpl(WebClient webClient, String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }
    @Override
    public void register(long chatId) {
        webClient.post().uri(baseUrl + tgChatUrl, chatId).retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        resp -> onClientErrorInternal(resp, "registering new chat")
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        resp -> onServerErrorInternal(resp, "registering new chat")
                )
                .bodyToMono(Void.class).block();
    }

    @Override
    public LinkResponse addLink(long chatId, String link) {
        return webClient
                .post()
                .uri(baseUrl + linksUrl).header("Tg-Chat-Id", String.valueOf(chatId))
                .bodyValue(new AddLinkRequest(link))
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        resp -> onClientErrorInternal(resp, "adding link to track")
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        resp -> onServerErrorInternal(resp, "adding link to track")
                )
                .bodyToMono(LinkResponse.class).block();
    }

    @Override
    public ListLinksResponse getLinksList(long chatId) {
        return webClient
                .get()
                .uri(baseUrl + linksUrl).header("Tg-Chat-Id", String.valueOf(chatId))
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        resp -> onClientErrorInternal(resp, "getting tracking links")
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        resp -> onServerErrorInternal(resp, "getting tracking links")
                )
                .bodyToMono(ListLinksResponse.class).block();
    }

    private Mono<? extends RuntimeException> onClientErrorInternal(ClientResponse resp, String when) {
        LOGGER.error("Incorrect Scrapper API request while " + when);
        return resp.bodyToMono(ApiErrorResponse.class).map(ApiClientErrorException::new);
    }

    private Mono<? extends RuntimeException> onServerErrorInternal(ClientResponse resp, String when) {
        LOGGER.error("Scrapper API server error while " + when);
        return resp.bodyToMono(ApiErrorResponse.class).map(ApiInternalServerErrorException::new);
    }
}
