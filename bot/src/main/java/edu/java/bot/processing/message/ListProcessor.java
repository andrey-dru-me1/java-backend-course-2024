package edu.java.bot.processing.message;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.db.emulation.Links;
import java.util.List;

public class ListProcessor implements CommandProcessor {

  private final Links links;

  public ListProcessor(Links links) {
    this.links = links;
  }

  @Override
  public SendMessage process(Long chatId, String message) {
    if(!links.containsKey(chatId))
      return new SendMessage(chatId, "Register yourself in bot via command /start first.");

    List<String> userLinks = links.get(chatId);
    if (userLinks.isEmpty()) return new SendMessage(chatId, "No links are tracked.");

    StringBuilder stringBuilder = new StringBuilder();
    for (String link : userLinks) {
      stringBuilder.append(link).append('\n');
    }
    return new SendMessage(chatId, stringBuilder.toString());
  }
}
