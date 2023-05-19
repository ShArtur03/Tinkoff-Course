package ru.tinkoff.edu.java.LinkParser.parsers;

import ru.tinkoff.edu.java.LinkParser.data.LinkData;
import ru.tinkoff.edu.java.LinkParser.data.StackOverFlowLinkData;

public final class StackOverFlowLinkParser extends Parser {

    private static final String linkPattern = "https://stackoverflow.com";
    @Override
    public LinkData parseLink(String url) {
        if (url.contains(linkPattern)) {
            String[] pieces = url.split("/");
            String  questionId = pieces[4];
            return new StackOverFlowLinkData(questionId);
        }
        return nextParse(url);
    }
}
