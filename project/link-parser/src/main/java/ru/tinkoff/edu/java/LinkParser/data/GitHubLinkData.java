package ru.tinkoff.edu.java.LinkParser.data;

public record GitHubLinkData(String username, String repository) implements LinkData {}