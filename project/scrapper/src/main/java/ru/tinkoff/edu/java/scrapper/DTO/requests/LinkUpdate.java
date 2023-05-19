package ru.tinkoff.edu.java.scrapper.DTO.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.List;

@Getter
@Setter
public record LinkUpdate(@NotNull @JsonProperty("id")long id,
                         @NotNull @JsonProperty("url")URI url,
                         @NotNull @JsonProperty("description")String description,
                         @NotNull @JsonProperty("tgChatIds")List<Long> tgChatIds) {
}