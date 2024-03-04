package edu.java.bot.processing.message;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.db.emulation.Links;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

public class UserMessageProcessorTest {

    @Test
    public void testHelp() {
        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);
        Links links = Mockito.mock(Links.class);

        UserMessageProcessor userMessageProcessor = new UserMessageProcessor(telegramBot, links);
        String helpMessage =
                "This bot is a notification aggregator which collects notifications from multiple services "
                        + "in single place. You can now track links under Github and StackOverflow services." + """


                        You can manage me using commands below:

                        /start - register new user
                        /help - show this message
                        /track - start tracking new link
                        /untrack - stop tracking link
                        /list - show a list of tracking links
                        """;

        userMessageProcessor.process(1L, "/help");

        Mockito.verify(telegramBot).execute(Mockito.argThat(x -> helpMessage.equals(x.getParameters().get("text"))));
    }

    @Test
    public void testStart() {
        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);
        Links links = Mockito.mock(Links.class);

        UserMessageProcessor userMessageProcessor = new UserMessageProcessor(telegramBot, links);
        String startMassage = "You are registered!";

        userMessageProcessor.process(1L, "/start");

        Mockito.verify(telegramBot).execute(Mockito.argThat(x -> startMassage.equals(x.getParameters().get("text"))));
    }

    @Test
    public void testListEmpty() {
        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);

        Links links = Mockito.mock(Links.class);
        Mockito.when(links.containsKey(1L)).thenReturn(true);
        Mockito.when(links.get(1L)).thenReturn(Set.of());

        UserMessageProcessor userMessageProcessor = new UserMessageProcessor(telegramBot, links);
        String listMessage = "No links are tracked.";

        userMessageProcessor.process(1L, "/list");

        Mockito.verify(telegramBot).execute(Mockito.argThat(x -> listMessage.equals(x.getParameters().get("text"))));
    }

    @Test
    public void testListNotRegistered() {
        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);

        Links links = Mockito.mock(Links.class);
        Mockito.when(links.containsKey(1L)).thenReturn(false);

        UserMessageProcessor userMessageProcessor = new UserMessageProcessor(telegramBot, links);
        String listMessage = "Register yourself in bot via command /start first.";

        userMessageProcessor.process(1L, "/list");

        Mockito.verify(telegramBot).execute(Mockito.argThat(x -> listMessage.equals(x.getParameters().get("text"))));
    }

    @Test
    public void testList() {
        Set<String> linkSet = Set.of("https://github.com/some/path", "https://github.com/some/other/path",
                                     "https://stackoverflow.com/some/path");

        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);

        Links links = Mockito.mock(Links.class);
        Mockito.when(links.containsKey(1L)).thenReturn(true);
        Mockito.when(links.get(1L)).thenReturn(linkSet);

        UserMessageProcessor userMessageProcessor = new UserMessageProcessor(telegramBot, links);

        userMessageProcessor.process(1L, "/list");

        Mockito.verify(telegramBot)
                .execute(Mockito.argThat(
                        x -> x.getParameters().get("text") instanceof String text && Arrays.stream(text.split("\n"))
                                .collect(Collectors.toSet())
                                .equals(linkSet)));
    }

    @Test
    public void testTrack() {
        Set<String> linkSet = Set.of("https://github.com/some/path", "https://github.com/some/other/path",
                                     "https://stackoverflow.com/some/path");

        TelegramBot telegramBot = Mockito.mock(TelegramBot.class);
        Links links = new Links();
        links.put(1L, new HashSet<>());

        UserMessageProcessor userMessageProcessor = new UserMessageProcessor(telegramBot, links);

        userMessageProcessor.process(1L, "/track");
        String trackMessage = "Write down links you want to track:";

        Mockito.verify(telegramBot)
                .execute(Mockito.argThat(
                        x -> x.getParameters().get("text") instanceof String text && trackMessage.equals(text)));

        userMessageProcessor.process(1L, String.join("\n", linkSet));
        Assertions.assertEquals(linkSet, links.get(1L));
    }
}
