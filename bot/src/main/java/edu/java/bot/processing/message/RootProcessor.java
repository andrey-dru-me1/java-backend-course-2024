package edu.java.bot.processing.message;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.db.emulation.Links;
import java.util.Map;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

public class RootProcessor implements CommandProcessor{

    private final Map<String, CommandProcessor> commands;

    public RootProcessor(Links links, UserMessageProcessor userMessageProcessor) {
        commands = Map.of("/start",
                           new StartProcessor(links),
                           "/help",
                           new HelpProcessor(),
                           "/track",
                           new TrackProcessor(links, userMessageProcessor),
                           "/untrack",
                           new UntrackProcessor(links, userMessageProcessor),
                           "/list",
                           new ListProcessor(links));
    }

    @Override
    public SendMessage process(Long chatId, String message) {
        CommandProcessor messageProcessor =
                commands.getOrDefault(message, commands.get("/help"));
        return messageProcessor.process(chatId, message);
    }
}
