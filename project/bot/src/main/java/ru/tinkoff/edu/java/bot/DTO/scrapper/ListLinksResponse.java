package ru.tinkoff.edu.java.bot.DTO.scrapper;

import java.util.List;

public record ListLinksResponse(
    List<LinkResponse> links,
    Integer size
) {
}