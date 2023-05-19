package ru.tinkoff.edu.java.bot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.DTO.controller.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class BotUpdatesController {

    private final Bot bot;

    @PostMapping(value = "/updates", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendUpdates(@RequestBody LinkUpdateRequest request) {
        bot.sendUpdates(request);
        log.info(request.toString());
    }
}
