package edu.java.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StackOverflowQuestion {
  private long questionId;
  private String[] tags;

  @JsonAlias("is_answered")
  private boolean isAnswered;

  private long viewCount;
  private int answerCount;
  private int score;
  private OffsetDateTime lastEditDate;
  private String title;
}
