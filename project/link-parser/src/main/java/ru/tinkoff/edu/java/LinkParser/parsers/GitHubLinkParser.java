package ru.tinkoff.edu.java.LinkParser.parsers;

import ru.tinkoff.edu.java.LinkParser.data.GitHubLinkData;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;

public final class GitHubLinkParser extends Parser{

    private static final String linkPattern = "https://github.com";

    @Override
    public LinkData parseLink(String url) {
        if (url.contains(linkPattern)) {
            String[] pieces = url.split("/");
            String user = pieces[3];
            String repo = pieces[4];
            return new GitHubLinkData(user, repo);
        }
        return nextParse(url);
    }
}
