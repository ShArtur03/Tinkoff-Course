package ru.tinkoff.edu.java.bot.telegram.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.DTO.request.LinkUpdate;
import ru.tinkoff.edu.java.bot.scrapper.exception.ChatNotFoundException;
import ru.tinkoff.edu.java.bot.scrapper.exception.LinkNotFoundException;

@Service
public class LinkService {


    public void updateLink(LinkUpdate linkUpdate) {
        if(linkUpdate.id() == 1){
            throw new ChatNotFoundException("Chat not found");
        }
        if(linkUpdate.id() == 2){
            throw new LinkNotFoundException("Link not found");
        }
    }
}
