package edu.java.bot.processing.message;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.db.emulation.Links;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UntrackProcessor implements CommandProcessor {

  private final Links links;
  private final UserMessageProcessor userMessageProcessor;

  public UntrackProcessor(Links links, UserMessageProcessor userMessageProcessor) {
    this.links = links;
    this.userMessageProcessor = userMessageProcessor;
  }

  @Override
  public SendMessage process(Long chatId, String message) {
    if (!links.containsKey(chatId)) {
      return new SendMessage(chatId, "Register yourself in bot via command /start first.");
    }
    userMessageProcessor.setProcessor(chatId, new ReadLinksProcessor());
    return new SendMessage(chatId, "Write down links you want to remove:");
  }

  public class ReadLinksProcessor implements CommandProcessor {

    @Override
    public SendMessage process(Long chatId, String message) {
      Set<String> userLinks = links.get(chatId);
      String[] linksToRemove = message.split("\\r?\\n");
      List<String> removedLinks = new ArrayList<>();
      for (String linkToRemove : linksToRemove) {
        if (userLinks.remove(linkToRemove)) {
          removedLinks.add(linkToRemove);
        }
      }

      String response;
      if (removedLinks.isEmpty()) {
        response = "No such links were detected.";
      } else if (removedLinks.size() == 1) {
        response = "Link is successfully removed!";
      } else {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("Links:\n");
        for (String removedLink : removedLinks) {
          responseBuilder.append(removedLink).append('\n');
        }
        responseBuilder.append("- are successfully removed!");
        response = responseBuilder.toString();
      }
      userMessageProcessor.resetProcessor(chatId);
      return new SendMessage(chatId, response);
    }
  }
}
