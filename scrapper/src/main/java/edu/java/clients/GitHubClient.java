package edu.java.clients;

import edu.java.dto.GitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GitHubClient {

  private final WebClient webClient;

  @Autowired
  public GitHubClient(WebClient.Builder webClientBuilder) {
    this(webClientBuilder, "https://api.github.com");
  }

  public GitHubClient(@Autowired WebClient.Builder webClientBuilder, String baseUrl) {
    this.webClient = webClientBuilder.baseUrl(baseUrl).build();
  }

  public Mono<GitHubRepository> getRepository(String owner, String repo) {
    return this.webClient
        .get()
        .uri("/repos/{owner}/{repo}", owner, repo)
        .retrieve()
        .bodyToMono(GitHubRepository.class);
  }
}
