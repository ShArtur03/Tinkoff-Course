package ru.tinkoff.edu.java.scrapper.DTO.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record StackOverFlowApiItemResponse(
        @JsonProperty("answer_count")Integer answersCount,
        @JsonProperty("is_answered")boolean isAnswered,
        @JsonProperty("creation_date")OffsetDateTime creationDate,
        @JsonProperty("last_activity_date")OffsetDateTime lastActivityDate,
        String title
) {
}
