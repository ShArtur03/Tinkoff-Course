package ru.tinkoff.edu.java.scrapper.service.domain.entitiesService;

import ru.tinkoff.edu.java.scrapper.DTO.model.Link;
import ru.tinkoff.edu.java.scrapper.DTO.model.Chat;

import java.net.URI;
import java.util.List;

public interface SubscriptionService {
    Link subscribe(Long chatId, URI url);
    Link unsubscribe(Long chatId, URI url);
    List<Link> getChatSubscriptions(Long chatId);
    List<Chat> getLinkSubscribers(Long linkId);
    default List<Long> getChatsIds(Long linkId) {
        return getLinkSubscribers(linkId)
                .stream()
                .map(Chat::getId)
                .toList();
    }
}