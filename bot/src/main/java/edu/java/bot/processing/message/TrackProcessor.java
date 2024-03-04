package edu.java.bot.processing.message;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.db.emulation.Links;
import java.util.List;

public class TrackProcessor implements CommandProcessor {

  private final Links links;
  private final UserMessageProcessor userMessageProcessor;

  public TrackProcessor(Links links, UserMessageProcessor userMessageProcessor) {
    this.links = links;
    this.userMessageProcessor = userMessageProcessor;
  }

  @Override
  public SendMessage process(Long chatId, String message) {
    if (!links.containsKey(chatId)) {
      return new SendMessage(chatId, "Register yourself in bot via command /start first.");
    }
    userMessageProcessor.setProcessor(chatId, new ReadLinksProcessor());
    return new SendMessage(chatId, "Write down links you want to track:");
  }

  public class ReadLinksProcessor implements CommandProcessor {

    @Override
    public SendMessage process(Long chatId, String message) {
      String[] userLinks = message.split("\\r?\\n");
      links.get(chatId).addAll(List.of(userLinks));
      userMessageProcessor.resetProcessor(chatId);
      return new SendMessage(
          chatId, "Link" + (userLinks.length > 1 ? "s" : "") + " successfully added!");
    }
  }
}
