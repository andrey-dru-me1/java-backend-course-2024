package edu.java;

import edu.java.clients.GitHubClient;
import edu.java.clients.StackOverflowClient;
import edu.java.dto.GitHubRepository;
import edu.java.dto.StackOverflowQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SomeEndPoint {

  private final GitHubClient gitHubClient;
  private final StackOverflowClient stackOverflowClient;

  @GetMapping("/some/request")
  public GitHubRepository makeSomeRequest() {
    return this.gitHubClient.getRepository("andrey-dru-me1", "java-backend-course-2024").block();
  }

  @GetMapping("/stackoverflow")
  public StackOverflowQuestion getStackOverflowQuestion() {
    return this.stackOverflowClient.getQuestion(76936608).block();
  }
}
