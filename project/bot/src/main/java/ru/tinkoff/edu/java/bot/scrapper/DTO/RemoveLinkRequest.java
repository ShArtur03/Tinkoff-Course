package ru.tinkoff.edu.java.bot.scrapper.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record RemoveLinkRequest(@NotNull @JsonProperty("link")String link) {
}
