package ru.tinkoff.edu.java.scrapper.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.DTO.requests.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.responses.LinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.responses.ListLinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.entities.LinkEntity;
import ru.tinkoff.edu.java.scrapper.service.SubscriptionService;

import java.net.URI;
import java.util.List;

@Validated
@RequestMapping("/links")
@RestController
@RequiredArgsConstructor
public class LinkController {

    private final SubscriptionService subscriptionService;

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkResponse createLink(@PathVariable("id") Long id, @RequestBody AddLinkRequest request) {
        LinkEntity linkEntity = subscriptionService.subscribe(id, URI.create(request.url()));
        return new LinkResponse(linkEntity.getId(), URI.create(linkEntity.getUrl()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListLinkResponse getLinks(@PathVariable("id") Long id) {
        List<LinkResponse> links = subscriptionService.getChatSubscriptions(id)
                .stream()
                .map(linkEntity -> new LinkResponse(linkEntity.getId(), URI.create(linkEntity.getUrl())))
                .toList();
        return new ListLinkResponse(links, links.size());
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkResponse delete(@PathVariable("id") Long id, @RequestBody RemoveLinkRequest request) {
        LinkEntity linkEntity = subscriptionService.unsubscribe(id, URI.create(request.url()));
        return new LinkResponse(linkEntity.getId(), URI.create(linkEntity.getUrl()));
    }
}
