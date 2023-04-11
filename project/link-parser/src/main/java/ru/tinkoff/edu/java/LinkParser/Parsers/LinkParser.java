package ru.tinkoff.edu.java.LinkParser.Parsers;

import ru.tinkoff.edu.java.LinkParser.LinksInfo.LinkInfo;

public sealed interface LinkParser permits Parser, GitLinkParser, StackLinkParser {

    //Метод реализации парсинга ссылки
    LinkInfo linkParsing(String url);

}
