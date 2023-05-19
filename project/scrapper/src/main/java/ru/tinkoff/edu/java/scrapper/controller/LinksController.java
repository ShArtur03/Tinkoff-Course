package ru.tinkoff.edu.java.scrapper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.DTO.model.Link;
import ru.tinkoff.edu.java.scrapper.DTO.controller.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.controller.LinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.controller.ListLinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.controller.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.service.domain.entitiesService.SubscriptionService;

import java.net.URI;
import java.util.List;

@Validated
@RequestMapping("/links")
@RestController
@RequiredArgsConstructor
public class LinksController {
    private final SubscriptionService subscriptionService;

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkResponse create(@PathVariable("id") Long id, @RequestBody AddLinkRequest request) {
        Link link = subscriptionService.subscribe(id, URI.create(request.link()));
        return new LinkResponse(link.getId(), URI.create(link.getUrl()));
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
        Link link = subscriptionService.subscribe(id, URI.create(String.valueOf(request.url())));
        return new LinkResponse(link.getId(), URI.create(link.getUrl()));
    }
}
