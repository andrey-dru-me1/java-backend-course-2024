package edu.java.clients;

import edu.java.dto.StackOverflowQuestion;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class StackOverflowClient {

  private final WebClient webClient;

  public StackOverflowClient(WebClient.Builder webClientBuilder) {
    this(webClientBuilder, "https://api.stackexchange.com");
  }

  public StackOverflowClient(WebClient.Builder webClientBuilder, String baseUrl) {
    this.webClient = webClientBuilder.baseUrl(baseUrl).build();
  }

  public Mono<StackOverflowQuestion> getQuestion(long questionId) {
    return this.webClient
        .get()
        .uri("/questions/{question_id}?order=desc&sort=activity&site=stackoverflow", questionId)
        .retrieve()
        .bodyToMono(StackOverflowQuestionList.class)
        .map(s -> s.getItems().getFirst());
  }

  @Getter
  @Setter
  private static class StackOverflowQuestionList {
    private List<StackOverflowQuestion> items;
  }
}
