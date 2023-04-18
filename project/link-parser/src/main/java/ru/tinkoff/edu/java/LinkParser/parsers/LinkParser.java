package ru.tinkoff.edu.java.LinkParser.parsers;

import ru.tinkoff.edu.java.LinkParser.data.LinkData;

public interface LinkParser {
    LinkData parseLink(String url);
}
