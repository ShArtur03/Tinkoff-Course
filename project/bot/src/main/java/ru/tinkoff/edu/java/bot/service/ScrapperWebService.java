package ru.tinkoff.edu.java.bot.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.DTO.scrapper.AddLinkRequest;
import ru.tinkoff.edu.java.bot.DTO.scrapper.LinkResponse;
import ru.tinkoff.edu.java.bot.DTO.scrapper.ListLinksResponse;
import ru.tinkoff.edu.java.bot.DTO.scrapper.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.clients.ScrapperWebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapperWebService {
    private final ScrapperWebClient client;

    public void createChat(Long id) {
        client.createChat(id);
    }

    public void deleteChat(Long id) {
        client.deleteChat(id);
    }

    public LinkResponse createLink(Long id, String link) {
        return client.createLink(id, new AddLinkRequest(link));
    }

    public ListLinksResponse getAllLinks(Long id) {
        return client.getAllLinks(id);
    }

    public LinkResponse deleteLink(Long id, String link) {
        return client.deleteLink(id, new RemoveLinkRequest(link));
    }
}
