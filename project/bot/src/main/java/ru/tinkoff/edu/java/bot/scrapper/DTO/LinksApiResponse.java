package ru.tinkoff.edu.java.bot.scrapper.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record LinksApiResponse(
        @NotNull @JsonProperty("description")String description,
        @NotNull @JsonProperty("code")String code,
        @NotNull @JsonProperty("exceptionName")String exceptionName,
        @NotNull @JsonProperty("exceptionMessage")String exceptionMessage,
        @NotNull @JsonProperty("stacktrace")String[] stacktrace) {
}
