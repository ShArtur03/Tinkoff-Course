package ru.tinkoff.edu.java.bot.service;

import ru.tinkoff.edu.java.LinkParser.LinksInfo.LinkInfo;

import java.util.List;

public interface LinkTracker{

    String getLinks();

    void addLink(String currentLink);
}
