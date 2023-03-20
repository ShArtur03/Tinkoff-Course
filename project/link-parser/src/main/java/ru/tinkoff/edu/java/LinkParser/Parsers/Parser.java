package ru.tinkoff.edu.java.LinkParser.Parsers;

import ru.tinkoff.edu.java.LinkParser.LinksInfo.LinkInfo;

public abstract non-sealed class Parser implements LinkParser {

    //Текущий парсер
    private Parser currentParser;

    //Для цепочки обязанностей парсеров
    public void setNextParser(Parser nextParser) {
        this.currentParser = nextParser;
    }

    //Проверяет ссылку до тех пор, пока не будут совпадения или при их отсутствии выведет null
    public LinkInfo nextURL(String url) {
        if (currentParser != null) return currentParser.linkParsing(url);
        return null;
    }
}
