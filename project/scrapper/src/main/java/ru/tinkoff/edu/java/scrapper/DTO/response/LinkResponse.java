package ru.tinkoff.edu.java.scrapper.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record LinkResponse(@NotNull @JsonProperty("id")long id, @NotNull @JsonProperty("url")String Url) {
}
