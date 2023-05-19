package ru.tinkoff.edu.java.bot.DTO.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ApiErrorResponse(
        @JsonProperty("description")String description,
        @JsonProperty("code")String code,
        @JsonProperty("exceptionName")String exceptionName,
        @JsonProperty("exceptionMessage")String exceptionMessage,
        @JsonProperty("stacktrace") List<String> stacktrace) {
}
