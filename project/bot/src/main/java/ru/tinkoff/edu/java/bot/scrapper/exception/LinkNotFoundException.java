package ru.tinkoff.edu.java.bot.scrapper.exception;

public class LinkNotFoundException extends RuntimeException{
    public LinkNotFoundException(String message) {
        super(message);
    }
}