package ru.tinkoff.edu.java.scrapper.clients.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GitHubApiResponse(
        Long id,
        @JsonProperty("full_name")
        String fullName,
        @JsonProperty("updated_at")
        OffsetDateTime updatedAt,
        @JsonProperty("created_at")
        OffsetDateTime createdAt
) {
}
