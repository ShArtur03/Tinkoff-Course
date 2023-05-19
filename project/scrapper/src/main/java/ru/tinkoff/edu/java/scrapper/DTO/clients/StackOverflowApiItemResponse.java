package ru.tinkoff.edu.java.scrapper.DTO.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;

public record StackOverflowApiItemResponse(
        String title,
        @JsonProperty("answer_count")
        Integer answersCount,
        @JsonProperty("is_answered")
        Boolean isAnswered,
        @JsonProperty("last_activity_date")
        OffsetDateTime lastActivityDate,
        @JsonProperty("creation_date")
        OffsetDateTime creationDate
) {
}
