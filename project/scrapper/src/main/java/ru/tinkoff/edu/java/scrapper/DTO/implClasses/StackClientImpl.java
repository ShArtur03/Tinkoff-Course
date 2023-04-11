package ru.tinkoff.edu.java.scrapper.DTO.implClasses;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.LinkParser.LinksInfo.StackInfo;
import ru.tinkoff.edu.java.scrapper.DTO.response.StackItemsResponse;
import ru.tinkoff.edu.java.scrapper.DTO.response.StackOverFlowApiResponse;
import ru.tinkoff.edu.java.scrapper.configuration.clientConfiguration.StackOverFlowClient;

public class StackClientImpl implements StackOverFlowClient {

    private final @NotNull WebClient webClient;
    private final @NotNull String StackUrl;
    private final static String StackUrlBase = "https://api.stackexchange.com/docs/questions/";

    public StackClientImpl(WebClient webClient) {
        this.webClient = webClient;
        this.StackUrl = StackUrlBase;
    }

    public StackClientImpl(String baseURL, WebClient webClient) {
        this.StackUrl = baseURL;
        this.webClient = webClient;
    }

    @Override
    public StackItemsResponse getQuestion(StackInfo link) {
        return webClient.get()
                .uri(StackUrl + link.questionId() + "?order=desc&sort=activity&site=stackoverflow")
                .retrieve().bodyToMono(StackOverFlowApiResponse.class).block().items().get(0);
    }
}
