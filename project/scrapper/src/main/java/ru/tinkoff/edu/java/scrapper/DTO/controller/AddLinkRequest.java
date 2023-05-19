package ru.tinkoff.edu.java.scrapper.DTO.controller;

import jakarta.validation.constraints.NotBlank;

import java.net.URI;

public record AddLinkRequest(@NotBlank String link) {
}