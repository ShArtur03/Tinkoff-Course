package ru.tinkoff.edu.java.LinkParser.Parsers;

import ru.tinkoff.edu.java.LinkParser.LinksInfo.LinkInfo;
import ru.tinkoff.edu.java.LinkParser.LinksInfo.StackInfo;

public non-sealed class StackLinkParser extends Parser implements LinkParser {

    //Метод с парсингом ссылки по переменной HOST и ключевому слову questions
    @Override
    public LinkInfo linkParsing(String url) {
        if (url.contains("stackoverflow.com") && url.contains("questions"))
            return new StackInfo(url.split("/")[4]);
        return nextURL(url);
    }
}
