package edu.java.bot.dto;

import java.net.URI;

public class LinkUpdateRequest {
    private long id;
    private URI url;
    private String description;
    private long[] tgChatIds;
}
