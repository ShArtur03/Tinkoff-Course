package ru.tinkoff.edu.java.bot.DTO.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record LinkUpdateRequest(
        @Positive @JsonProperty("id")long id,
        @NotBlank @JsonProperty("url")String url,
        @NotBlank @JsonProperty("description")String description,
        @NotNull @JsonProperty("tgChatIds") List<Long> tgChatIds) {

}
