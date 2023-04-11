package ru.tinkoff.edu.java.scrapper.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record AddLinkRequest(@NotNull @JsonProperty("link") String link) {
}
