package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.DTO.clients.StackOverFlowApiItemResponse;
import ru.tinkoff.edu.java.scrapper.DTO.clients.StackOverFlowApiItemsResponse;

@HttpExchange(accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)

public interface StackOverFlowClient {
    @GetExchange("/questions/{id}?site=stackoverflow")
    Mono<StackOverFlowApiItemResponse> fetchQuestion(@PathVariable("id") Integer id);

    @GetExchange("/questions/{ids}?site=stackoverflow")
    Mono<StackOverFlowApiItemsResponse> fetchQuestions(@PathVariable("ids") String ids);
}
