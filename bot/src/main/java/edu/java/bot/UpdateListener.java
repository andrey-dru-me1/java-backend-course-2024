package edu.java.bot;

import edu.java.bot.configuration.ApplicationConfig;
import org.springframework.stereotype.Component;

@Component
public class UpdateListener {

    public UpdateListener(ApplicationConfig applicationConfig, Bot bot) {
        applicationConfig.telegramBot().setUpdatesListener(bot);
    }

}
