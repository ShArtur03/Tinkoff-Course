package ru.tinkoff.edu.java.bot.scrapper.exception;

public class ChatNotFoundException extends RuntimeException{
    public ChatNotFoundException(String message) {
        super(message);
    }
}