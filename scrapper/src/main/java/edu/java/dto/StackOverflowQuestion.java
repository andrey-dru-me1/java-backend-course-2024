package edu.java.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StackOverflowQuestion {
  private long question_id;
  private String[] tags;

  @JsonAlias("is_answered")
  private boolean is_answered;

  private long view_count;
  private int answer_count;
  private int score;
  private OffsetDateTime last_edit_date;
  private String title;
}
