package edu.java.bot.processing.message;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.db.emulation.Links;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class UserMessageProcessor {

  private final CommandProcessor defaultProcessor;
  private final TelegramBot telegramBot;
  @Setter private CommandProcessor processor;

  public UserMessageProcessor(TelegramBot telegramBot, Links links) {
    processor = new RootProcessor(links, this);
    this.telegramBot = telegramBot;
    defaultProcessor = processor;
  }

  public void resetProcessor() {
    this.processor = defaultProcessor;
  }

  public void process(Long chatId, String message) {
    telegramBot.execute(processor.process(chatId, message));
  }
}
