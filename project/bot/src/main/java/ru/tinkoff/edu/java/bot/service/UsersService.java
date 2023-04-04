package ru.tinkoff.edu.java.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

public final class UsersService {

    private final List<Users> users;


    public UsersService() {
        users = new ArrayList<>();
    }

    public boolean addUser(Users user) throws InterruptedException {
        return users.add(user);
    }

    public boolean removeUser(Users user){
        return users.removeIf(a -> a.getUser().equals(user));
    }

}
