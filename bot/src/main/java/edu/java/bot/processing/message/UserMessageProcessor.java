package edu.java.bot.processing.message;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.db.emulation.Links;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserMessageProcessor {

    private final CommandProcessor defaultProcessor;
    private final TelegramBot telegramBot;
    private final Map<Long, CommandProcessor> processors;

    public UserMessageProcessor(TelegramBot telegramBot, Links links) {
        processors = new HashMap<>();
        defaultProcessor = new RootProcessor(links, this);
        this.telegramBot = telegramBot;
    }

    public void setProcessor(Long chatId, CommandProcessor processor) {
        this.processors.put(chatId, processor);
    }

    public void resetProcessor(Long chatId) {
        this.processors.put(chatId, defaultProcessor);
    }

    public void process(Long chatId, String message) {
        processors.putIfAbsent(chatId, defaultProcessor);
        telegramBot.execute(processors.get(chatId).process(chatId, message));
    }
}
