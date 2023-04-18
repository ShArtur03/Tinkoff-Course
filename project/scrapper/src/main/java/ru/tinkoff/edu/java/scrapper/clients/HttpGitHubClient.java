package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.LinkParser.data.GitHubLinkData;
import ru.tinkoff.edu.java.scrapper.clients.DTO.GitHubApiResponse;

public class HttpGitHubClient implements GitHubClient{

    private static final String gitUrl = "https://api.github.com/repos/";

    private final String baseUrl;
    private final WebClient webClient;

    public HttpGitHubClient(WebClient webClient){
        this.webClient = webClient;
        this.baseUrl = gitUrl;
    }

    public HttpGitHubClient(WebClient webClient, String baseUrl){
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }
    @Override
    public GitHubApiResponse getRepository(GitHubLinkData userAndRepo) {
        return webClient
                .get()
                .uri(baseUrl + userAndRepo.username() + "/" + userAndRepo.repository())
                .retrieve()
                .bodyToMono(GitHubApiResponse.class)
                .block();
    }
}
