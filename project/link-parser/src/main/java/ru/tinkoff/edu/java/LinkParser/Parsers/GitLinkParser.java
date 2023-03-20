package ru.tinkoff.edu.java.LinkParser.Parsers;

import ru.tinkoff.edu.java.LinkParser.LinksInfo.GitHubLinkInfo;
import ru.tinkoff.edu.java.LinkParser.LinksInfo.LinkInfo;

public non-sealed class GitLinkParser extends Parser implements LinkParser{

    //Метод с парсингом ссылки по "github.com"
    @Override
    public LinkInfo linkParsing(String url) {
        if (url.contains("github.com"))
            return new GitHubLinkInfo(url.split("/")[3], url.split("/")[4]);
        return nextURL(url);
    }
}
