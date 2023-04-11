package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.scrapper.Clients.LinksClient;
import ru.tinkoff.edu.java.bot.scrapper.DTO.AddLinkRequest;

@Component
public class TrackCommand implements Command{

    private final LinksClient scrapperClient;

    public TrackCommand(LinksClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Начать отслеживать ссылку.";
    }

    @Override
    public SendMessage handle(Update update) {
        String message = update.message().text();
        if (message.charAt(0) == '/') return new SendMessage(update.message().chat().id(), "Введите ссылку");
        scrapperClient.addLink(update.message().chat().id(), new AddLinkRequest(update.message().text()));
        return new SendMessage(update.message().chat().id(), "Ссылка отслеживается");
    }
}