package edu.java.bot.processing.message;

import com.pengrad.telegrambot.request.SendMessage;

public class HelpProcessor implements CommandProcessor {

  @Override
  public SendMessage process(Long chatId, String message) {
    return new SendMessage(
        chatId,
        "This bot is a notification aggregator which collects notifications from multiple services "
            + "in single place. You can now track links under Github and StackOverflow services."
            + """


                        You can manage me using commands below:

                        /start - register new user
                        /help - show this message
                        /track - start tracking new link
                        /untrack - stop tracking link
                        /list - show a list of tracking links
                        """);
  }
}
