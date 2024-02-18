package edu.java.bot.processing.message;

import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.db.emulation.Links;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class UserMessageProcessor {

  private final CommandProcessor defaultProcessor;
  private final ApplicationConfig applicationConfig;
  @Setter private CommandProcessor processor;

  public UserMessageProcessor(ApplicationConfig applicationConfig, Links links) {
    processor = new RootProcessor(links, this);
    this.applicationConfig = applicationConfig;
    defaultProcessor = processor;
  }

  public void resetProcessor() {
    this.processor = defaultProcessor;
  }

  public void process(Long chatId, String message) {
    applicationConfig.telegramBot().execute(processor.process(chatId, message));
  }
}
