package edu.java.bot.processing.message;

import com.pengrad.telegrambot.request.SendMessage;

public interface CommandProcessor {
    SendMessage process(Long chatId, String message);
}
