package ru.tinkoff.edu.java.scrapper.DTO.controller;

import jakarta.validation.constraints.NotBlank;

public record RemoveLinkRequest(@NotBlank String url) {
}