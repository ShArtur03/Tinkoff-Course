package ru.tinkoff.edu.java.LinkParser.parsers;

import ru.tinkoff.edu.java.linkParser.data.LinkData;

public sealed abstract class Parser implements LinkParser permits StackOverFlowLinkParser, GitHubLinkParser{

    private LinkParser parser;
    public void setParser(LinkParser parser) {
        this.parser = parser;
    }

    protected LinkData nextParse(String url) {
        if (parser != null)
            return parser.parseLink(url);
        return null;
    }
}
