package ru.tinkoff.edu.java.bot.DTO;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.DTO.controller.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.service.LinkService;

@RestController
public class BotController {

    private final LinkService linkService;

    public BotController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping(value = "/updates")
    public void createMessage(@Valid @RequestBody LinkUpdateRequest request) {
        linkService.updateLink(request);
    }
}
