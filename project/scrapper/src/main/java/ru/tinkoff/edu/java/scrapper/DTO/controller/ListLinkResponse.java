package ru.tinkoff.edu.java.scrapper.DTO.controller;

import java.util.List;

public record ListLinkResponse(List<LinkResponse> links, Integer size) {
}