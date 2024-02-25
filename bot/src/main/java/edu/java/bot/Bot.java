package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.processing.message.UserMessageProcessor;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class Bot implements UpdatesListener {

  private final UserMessageProcessor userMessageProcessor;
  private final TelegramBot telegramBot;

  @PostConstruct
  public void setUpdatesListener() {
    telegramBot.setUpdatesListener(this);
  }

  @Override
  public int process(List<Update> list) {
    for (Update update : list) {
      Long chatId = update.message().chat().id();
      String text = update.message().text();
      log.info("Message from user received. chatId: {}, message: {}, self: {}", chatId, text, this);
      userMessageProcessor.process(chatId, text);
    }
    return CONFIRMED_UPDATES_ALL;
  }
}
