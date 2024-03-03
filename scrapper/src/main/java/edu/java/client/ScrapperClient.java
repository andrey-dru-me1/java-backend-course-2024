package edu.java.client;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import edu.java.dto.RemoveLinkRequest;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SuppressWarnings("MultipleStringLiterals")
public class ScrapperClient {

    private final WebClient webClient;

    public ScrapperClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public void postTgChat(long id) {
        this.webClient
                .post()
                .uri("/tg-chats/{id}", id)
                .retrieve();
    }

    public void deleteTgChat(long id) {
        this.webClient
                .delete()
                .uri("/tg-chats/{id}")
                .retrieve();
    }

    public Mono<ListLinksResponse> getLinks() {
        return this.webClient
                .get()
                .uri("/links")
                .retrieve()
                .bodyToMono(ListLinksResponse.class);
    }

    public Mono<LinkResponse> postLinks(AddLinkRequest link) {
        return this.webClient
                .post()
                .uri("/links")
                .body(BodyInserters.fromValue(link))
                .retrieve()
                .bodyToMono(LinkResponse.class);
    }

    public Mono<LinkResponse> deleteLinks(RemoveLinkRequest link) {
        return this.webClient
                .method(HttpMethod.DELETE)
                .uri("/links")
                .body(BodyInserters.fromValue(link))
                .retrieve()
                .bodyToMono(LinkResponse.class);
    }

}
