package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Component;

@Component
public class UpdateListener {

  public UpdateListener(TelegramBot telegramBot, Bot bot) {
    telegramBot.setUpdatesListener(bot);
  }
}
