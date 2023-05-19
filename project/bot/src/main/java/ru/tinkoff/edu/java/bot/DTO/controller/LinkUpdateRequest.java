package ru.tinkoff.edu.java.bot.DTO.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record LinkUpdateRequest(
        @NotNull @JsonProperty("id")long id,
        @NotNull @JsonProperty("url")String url,
        @NotNull @JsonProperty("description")String description,
        @NotNull @JsonProperty("tgChatIds")long[] tgChatIds) {

}
