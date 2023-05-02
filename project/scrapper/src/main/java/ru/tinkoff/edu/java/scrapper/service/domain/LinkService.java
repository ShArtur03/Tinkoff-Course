package ru.tinkoff.edu.java.scrapper.service.domain;

import ru.tinkoff.edu.java.scrapper.DTO.model.Link;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

public interface LinkService {
    List<Link> updateLastCheckedTimeAndGet(Duration linkToBeCheckedInterval);
    void updateLinkLastUpdateTime(Long id, OffsetDateTime newUpdateTime);
}