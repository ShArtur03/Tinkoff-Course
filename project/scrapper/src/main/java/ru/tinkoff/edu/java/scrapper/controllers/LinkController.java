package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.DTO.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.LinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.ListLinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.RemoveLinkRequest;

import java.util.ArrayList;

@RequestMapping("/links")
@RestController
public class LinkController {

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkResponse createLink(Long id, @RequestBody AddLinkRequest request) {
        return new LinkResponse(1L, "url.like.url.com/path");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListLinkResponse getLinks(Long id) {
        return new ListLinkResponse(new ArrayList<>(), 0);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkResponse delete(Long id, @RequestBody RemoveLinkRequest request) {
        return new LinkResponse(1L, "url.like.url.com/path");
    }
}
