package ru.tinkoff.edu.java.bot.scrapper.Clients;


import ru.tinkoff.edu.java.bot.scrapper.DTO.LinkResponse;
import ru.tinkoff.edu.java.bot.scrapper.DTO.ListLinksResponse;

import java.net.URI;

public interface ScrapperClient {

    void register(long chatId);

    LinkResponse addLink(long chatId, String link);

    ListLinksResponse getLinksList(long chatId);

}
