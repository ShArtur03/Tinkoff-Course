package ru.tinkoff.edu.java.scrapper.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.service.domain.ChatService;

@Validated
@RequestMapping("/tg-chat")
@RestController
@RequiredArgsConstructor
public class TGChatController {

    @Autowired
    private final ChatService chatService;

    @PostMapping(value = "/{id}")
    public void create(@PathVariable("id") Long id) {
        chatService.register(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        chatService.unregister(id);
    }
}
