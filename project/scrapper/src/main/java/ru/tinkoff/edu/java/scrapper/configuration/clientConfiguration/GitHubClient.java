package ru.tinkoff.edu.java.scrapper.configuration.clientConfiguration;

import ru.tinkoff.edu.java.LinkParser.LinksInfo.GitHubLinkInfo;
import ru.tinkoff.edu.java.scrapper.DTO.response.GitHubApiResponse;

public interface GitHubClient {

    GitHubApiResponse getRepository(GitHubLinkInfo link);

}
