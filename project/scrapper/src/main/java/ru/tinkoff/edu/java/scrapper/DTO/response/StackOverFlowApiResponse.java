package ru.tinkoff.edu.java.scrapper.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StackOverFlowApiResponse(@NotNull @JsonProperty("items") List<StackItemsResponse> items) {
}

