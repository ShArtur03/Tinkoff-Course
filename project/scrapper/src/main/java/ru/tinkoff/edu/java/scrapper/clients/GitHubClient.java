package ru.tinkoff.edu.java.scrapper.clients;

import ru.tinkoff.edu.java.LinkParser.data.GitHubLinkData;
import ru.tinkoff.edu.java.scrapper.clients.DTO.GitHubApiResponse;

public interface GitHubClient {
    GitHubApiResponse getRepository(GitHubLinkData userAndRepo);
}
