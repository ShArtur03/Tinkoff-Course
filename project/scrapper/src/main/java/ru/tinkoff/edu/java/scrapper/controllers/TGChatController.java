package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tg-chat")
@RestController
public class TGChatController {
    @PostMapping(value = "/{id}")
    public void create(Long id) {}

    @DeleteMapping(value = "/{id}")
    public void delete(Long id) {}
}
