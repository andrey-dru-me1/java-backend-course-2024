package edu.java.dto;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitHubRepository {
  private long id;
  private String name;
  private String full_name;
  private OffsetDateTime updated_at;
}
