package edu.java.bot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Bot implements UpdatesListener {

    private final UserMessageProcessor userMessageProcessor;

    public Bot(UserMessageProcessor userMessageProcessor) {
        this.userMessageProcessor = userMessageProcessor;
    }

    @Override
    public int process(List<Update> list) {
        for (Update update : list) {
            Long chatId = update.message().chat().id();
            String text = update.message().text();
            userMessageProcessor.process(chatId, text);
        }
        return com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
