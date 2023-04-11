package ru.tinkoff.edu.java.scrapper.DTO.implClasses;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.LinkParser.LinksInfo.GitHubLinkInfo;
import ru.tinkoff.edu.java.scrapper.DTO.response.GitHubApiResponse;
import ru.tinkoff.edu.java.scrapper.configuration.clientConfiguration.GitHubClient;

public class GHClientImpl implements GitHubClient {


    private final @NotNull WebClient webClient;
    private final @NotNull String GHUrl;
    private final static String GHUrlBase = "https://api.github.com/repos/";

    public GHClientImpl(WebClient webClient) {
        this.webClient = webClient;
        this.GHUrl = GHUrlBase;
    }

    public GHClientImpl(String baseURL, WebClient webClient) {
        this.GHUrl = baseURL;
        this.webClient = webClient;
    }

    @Override
    public GitHubApiResponse getRepository(GitHubLinkInfo link) {
        return webClient.get().uri(GHUrl + link.user() + "/" + link.repository()).retrieve().bodyToMono(GitHubApiResponse.class).block();
    }
}
