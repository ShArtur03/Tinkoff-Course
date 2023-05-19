package ru.tinkoff.edu.java.scrapper.DTO.responses;

import java.util.List;

public record ListLinkResponse(List<LinkResponse> links, Integer size) {
}