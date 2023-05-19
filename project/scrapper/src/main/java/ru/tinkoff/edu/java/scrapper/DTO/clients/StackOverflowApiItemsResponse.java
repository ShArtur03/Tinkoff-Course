package ru.tinkoff.edu.java.scrapper.DTO.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StackOverflowApiItemsResponse(
        List<StackOverflowApiItemResponse> items,
        @JsonProperty("has_more")
        Boolean hasMore
) {
}
