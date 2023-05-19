package ru.tinkoff.edu.java.scrapper.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.DTO.clients.bot.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.clients.BotWebClient;
import ru.tinkoff.edu.java.scrapper.service.UpdatesSender;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false",  matchIfMissing = true)
public class BotWebService implements UpdatesSender {
    private final BotWebClient webClient;

    public void sendUpdate(LinkUpdateRequest request) {
        webClient.sendUpdate(request);
    }

}
