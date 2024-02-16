package edu.java.bot;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.configuration.ApplicationConfig;
import org.springframework.stereotype.Component;

@Component
public class UserMessageProcessor {

    private final ApplicationConfig applicationConfig;


    public UserMessageProcessor(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public void process(Long chatId, String message) {
        SendMessage response = new SendMessage(chatId, "Response on your message: " + message);
        applicationConfig.telegramBot().execute(response);
    }

}
