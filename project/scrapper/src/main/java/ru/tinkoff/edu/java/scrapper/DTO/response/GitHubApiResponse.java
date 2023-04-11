package ru.tinkoff.edu.java.scrapper.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record GitHubApiResponse(@NotNull @JsonProperty("id") long id,
                                @NotNull @JsonProperty("clone_url") String cloneUrl,
                                @NotNull @JsonProperty("name") String repoName,
                                @NotNull @JsonProperty("private") boolean isPrivate,
                                @NotNull @JsonProperty("created_at") OffsetDateTime createdAt,
                                @NotNull @JsonProperty("updated_at") OffsetDateTime updatedAt,
                                @NotNull @JsonProperty("pushed_at") OffsetDateTime pushedAt) {
}
