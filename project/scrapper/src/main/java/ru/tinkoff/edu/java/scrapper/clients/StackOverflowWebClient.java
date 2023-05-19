package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.DTO.clients.StackOverflowApiItemsResponse;
import ru.tinkoff.edu.java.scrapper.DTO.clients.StackOverflowApiResponse;

@HttpExchange(accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)

public interface StackOverflowWebClient {
    @GetExchange("/questions/{id}?site=stackoverflow")
    Mono<StackOverflowApiItemsResponse> fetchQuestion(@PathVariable("id") Integer id);

    @GetExchange("/questions/{ids}?site=stackoverflow")
    Mono<StackOverflowApiResponse> fetchQuestions(@PathVariable("ids") String ids);
}
