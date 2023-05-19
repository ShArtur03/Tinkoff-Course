package ru.tinkoff.edu.java.bot.exception;

public class LinkIsAlreadyTackingException extends Exception{
    public LinkIsAlreadyTackingException() {
    }

    public LinkIsAlreadyTackingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkIsAlreadyTackingException(String message) {
        super(message);
    }

    public LinkIsAlreadyTackingException(Throwable cause) {
        super(cause);
    }
}
