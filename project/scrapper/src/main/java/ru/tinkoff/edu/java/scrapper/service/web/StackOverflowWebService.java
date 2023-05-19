package ru.tinkoff.edu.java.scrapper.service.web;

import jakarta.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.tinkoff.edu.java.scrapper.DTO.clients.StackOverflowApiItemResponse;
import ru.tinkoff.edu.java.scrapper.DTO.clients.StackOverflowApiItemsResponse;
import ru.tinkoff.edu.java.scrapper.DTO.clients.UpdatesInfo;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowWebClient;


@Service
@RequiredArgsConstructor
public class StackOverflowWebService {
    private final StackOverflowWebClient stackOverflowWebClient;

    public @Nullable UpdatesInfo fetchQuestionUpdates(Integer id) {
        StackOverflowApiItemsResponse response = stackOverflowWebClient.fetchQuestion(id).block();
        if (!response.items().isEmpty()) {
            StackOverflowApiItemResponse questionResponse = response.items().get(0);
            return new UpdatesInfo(
                    questionResponse.lastActivityDate(),
                    List.of("Check out new update from " + questionResponse.title())
            );
        }
        return null;
    }

    public List<StackOverflowApiItemResponse> fetchQuestions(List<Integer> ids) {
        return stackOverflowWebClient
                .fetchQuestions(ids
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(";")))
                .map(StackOverflowApiItemsResponse::items).block();
    }
}
