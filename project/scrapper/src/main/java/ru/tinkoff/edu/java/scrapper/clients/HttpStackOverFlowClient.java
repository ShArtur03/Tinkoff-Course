package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.LinkParser.data.StackOverFlowLinkData;
import ru.tinkoff.edu.java.scrapper.clients.DTO.StackOverFlowApiItemsResponse;
import ru.tinkoff.edu.java.scrapper.clients.DTO.StackOverFlowApiResponse;

import java.util.Objects;

public class HttpStackOverFlowClient implements StackOverFlowClient{

    private static final String stackUrl = "https://api.stackexchange.com/2.3/questions/";

    private final String baseUrl;
    private final WebClient webClient;

    public HttpStackOverFlowClient(WebClient webClient) {
        this.webClient = webClient;
        this.baseUrl = stackUrl;
    }

    public HttpStackOverFlowClient(WebClient webClient, String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }

    @Override
    public StackOverFlowApiItemsResponse getQuestion(StackOverFlowLinkData question) {
        return Objects.requireNonNull(webClient
                .get()
                .uri(baseUrl + question.questionId() + "{id}?site=stackoverflow")
                .retrieve()
                .bodyToMono(StackOverFlowApiResponse.class)
                .block()).items().get(0);
    }
}
