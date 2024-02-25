package edu.java.bot.processing.message;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.db.emulation.Links;
import java.util.HashSet;

public class StartProcessor implements CommandProcessor {

  private final Links links;

  public StartProcessor(Links links) {
    this.links = links;
  }

  @Override
  public SendMessage process(Long chatId, String message) {
    links.put(chatId, new HashSet<>());
    return new SendMessage(chatId, "You are registered!");
  }
}
