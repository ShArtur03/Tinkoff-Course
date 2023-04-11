package ru.tinkoff.edu.java.bot.scrapper.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ListLinksResponse(@NotNull @JsonProperty("links") List<Links> links, @NotNull @JsonProperty("size")int size) {
}
