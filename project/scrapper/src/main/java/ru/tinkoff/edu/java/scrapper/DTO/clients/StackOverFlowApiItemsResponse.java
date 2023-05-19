package ru.tinkoff.edu.java.scrapper.DTO.clients;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StackOverFlowApiItemsResponse(
        List<StackOverFlowApiItemResponse> items,
        @JsonProperty("has_more")
        Boolean hasMore
) {
}
