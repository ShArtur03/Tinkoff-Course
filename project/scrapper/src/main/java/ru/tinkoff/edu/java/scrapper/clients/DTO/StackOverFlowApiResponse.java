package ru.tinkoff.edu.java.scrapper.clients.DTO;

import java.util.List;

public record StackOverFlowApiResponse(
        List<StackOverFlowApiItemsResponse> items
) {
}
