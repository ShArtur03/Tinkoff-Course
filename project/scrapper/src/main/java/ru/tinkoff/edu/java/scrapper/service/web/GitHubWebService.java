package ru.tinkoff.edu.java.scrapper.service.web;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.DTO.clients.GitHubEventResponse;
import ru.tinkoff.edu.java.scrapper.DTO.clients.UpdatesInfo;
import ru.tinkoff.edu.java.scrapper.clients.GitHubWebClient;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitHubWebService {
    private final GitHubWebClient gitHubWebClient;

    public @Nullable UpdatesInfo fetchEventsUpdates(String owner, String repo, OffsetDateTime lastUpdateTimeSaved) {
        List<GitHubEventResponse> events = gitHubWebClient.fetchEvents(owner, repo).block();
        if(!events.isEmpty()) {
            GitHubEventResponse lastEvent = events.get(0);
            List<String> eventsInfo = events.stream().filter(event -> event.getCreatedAt().isAfter(lastUpdateTimeSaved))
                    .map(event -> getEventTypeDescription(event.getType()) + " at " + event.getCreatedAt())
                    .toList();
            return new UpdatesInfo(lastEvent.getCreatedAt(), eventsInfo);
        }
        return null;
    }

    private String getEventTypeDescription(String eventType) {
        try {
            return GitHubEventType.valueOf(eventType).getDescription();
        } catch (IllegalArgumentException ignored) {
            return GitHubEventType.UnknownEvent.getDescription();
        }
    }
}
