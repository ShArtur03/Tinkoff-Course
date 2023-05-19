package ru.tinkoff.edu.java.scrapper.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.LinkParser.data.GitHubLinkData;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;
import ru.tinkoff.edu.java.LinkParser.data.StackOverFlowLinkData;
import ru.tinkoff.edu.java.LinkParser.parsers.ParserChain;
import ru.tinkoff.edu.java.scrapper.DTO.clients.UpdatesInfo;
import ru.tinkoff.edu.java.scrapper.DTO.clients.bot.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.DTO.model.Link;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.exception.InternalError;
import ru.tinkoff.edu.java.scrapper.service.domain.entitiesService.LinkService;
import ru.tinkoff.edu.java.scrapper.service.domain.entitiesService.SubscriptionService;
import ru.tinkoff.edu.java.scrapper.service.web.GitHubWebService;
import ru.tinkoff.edu.java.scrapper.service.web.StackOverflowWebService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkUpdatesService {
    private final ApplicationConfig config;
    private final ParserChain handlerChain;
    private final LinkService linkService;
    private final SubscriptionService subscriptionService;
    private final GitHubWebService gitHubWebService;
    private final StackOverflowWebService stackOverflowWebService;
    private final UpdatesSendingService updatesSendingService;

    public void updateLinks() {
        List<Link> uncheckedLinks = getUncheckedLinks();
        log.info(uncheckedLinks.stream().map(Link::toString).toList().toString());
        uncheckedLinks.forEach(link -> {
            UpdatesInfo updatesInfo = fetchUpdates(link);
            boolean shouldSendUpdate = updatesInfo != null &&
                    (link.getLastUpdateTime() == null ||
                            link.getLastUpdateTime().isBefore(updatesInfo.lastUpdateTime()));
            if (shouldSendUpdate) {
                sendUpdates(link, updatesInfo);
            }
        });
    }

    private List<Link> getUncheckedLinks() {
        return linkService.updateLastCheckedTimeAndGet(
                config.getScheduler().getLinkToBeCheckedInterval()
        );
    }

    private @Nullable UpdatesInfo fetchUpdates(Link link) {
        LinkData linkData = handlerChain.handle(link.getUrl());
        return switch (linkData) {
            case null -> throw new InternalError("Malicious link");
            case GitHubLinkData data ->
                    gitHubWebService.fetchEventsUpdates(data.username(), data.repository(), link.getLastUpdateTime());
            case StackOverFlowLinkData data -> stackOverflowWebService.fetchQuestionUpdates(data.questionId());
        };
    }

    private void sendUpdates(Link link, UpdatesInfo updatesInfo) {
        log.info("Sending updates");
        linkService.updateLinkLastUpdateTime(link.getId(), updatesInfo.lastUpdateTime());
        updatesSendingService.sendUpdate(new LinkUpdateRequest(
                link.getId(),
                link.getUrl(),
                Strings.join(updatesInfo.updates(), '\n'),
                subscriptionService.getChatsIds(link.getId())
        ));
    }
}
