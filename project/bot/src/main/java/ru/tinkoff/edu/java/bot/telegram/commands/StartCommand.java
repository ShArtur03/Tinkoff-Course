package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.scrapper.Clients.TelegramClient;

@Component
public class StartCommand implements Command{
    private final TelegramClient scrapperClient;

    public StartCommand(TelegramClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Старт бота";
    }

    @Override
    public SendMessage handle(Update update) {
        scrapperClient.register(update.message().chat().id());
        return new SendMessage(update.message().chat().id(), "Введите команду /help");
    }
}