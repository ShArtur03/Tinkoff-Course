package ru.tinkoff.edu.java.scrapper.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record StackItemsResponse(@NotNull @JsonProperty("question_id") long questionId,
                                 @NotNull @JsonProperty("tags") Set<String> tags,
                                 @NotNull @JsonProperty("is_answered") boolean isAnswered,
                                 @NotNull @JsonProperty("protected_date") OffsetDateTime protectedDate,
                                 @NotNull @JsonProperty("last_activity_date") OffsetDateTime lastActivityDate,
                                 @NotNull @JsonProperty("creation_date") OffsetDateTime creationDate,
                                 @NotNull @JsonProperty("last_edit_date") OffsetDateTime lastEditDate,
                                 @NotNull @JsonProperty("link") String link) {

}
