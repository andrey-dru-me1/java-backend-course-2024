package edu.java.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class BotClient {

    private final WebClient webClient;

    public BotClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public void postUpdates(LinkUpdateRequest linkUpdateRequest) {
        this.webClient
                .post()
                .uri("/updates")
                .body(BodyInserters.fromValue(linkUpdateRequest))
                .retrieve();
    }

    @Getter
    @Setter
    public static class LinkUpdateRequest {
        private long id;
        private String url;
        private String description;
        private long[] tgChatIds;
    }

}
