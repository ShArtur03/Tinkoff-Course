package ru.tinkoff.edu.java.bot.service;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

public final class Users{

    private final User user;
    private final Chat chat;

    public Users(User user, Chat chat) {
        if(user == null || chat == null)
            throw new IllegalStateException("User or chat cannot be null");
        this.user = user;
        this.chat = chat;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Users && ((Users) obj).getUser().equals(user);
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    public User getUser() {
        return user;
    }

    public Chat getChat() {
        return chat;
    }
}