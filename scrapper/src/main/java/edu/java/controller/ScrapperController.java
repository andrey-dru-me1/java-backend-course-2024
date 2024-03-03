package edu.java.controller;

import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperController {
    @PostMapping("/tg-chat/{id}")
    public void postTgChat(@PathVariable String id) {

    }

    @DeleteMapping("/tg-chat/{id}")
    public void deleteTgChat(@PathVariable String id) {

    }

    @GetMapping("/links")
    public ListLinksResponse getLinks() {
        return new ListLinksResponse();
    }

    @PostMapping("/links")
    public LinkResponse postLinks() {
        return new LinkResponse();
    }

    @DeleteMapping("/links")
    public LinkResponse deleteLinks() {
        return new LinkResponse();
    }

}
