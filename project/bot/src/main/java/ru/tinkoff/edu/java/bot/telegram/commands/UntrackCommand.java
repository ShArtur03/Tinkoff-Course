package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.scrapper.Clients.LinksClient;
import ru.tinkoff.edu.java.bot.scrapper.DTO.RemoveLinkRequest;
@Component
public class UntrackCommand implements Command{
    private final LinksClient scrapperClient;

    public UntrackCommand(LinksClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "Перестать отслеживать ссылку.";
    }

    @Override
    public SendMessage handle(Update update) {
        String message = update.message().text();
        if (message.charAt(0) == '/') return new SendMessage(update.message().chat().id(), "Введите ссылку");
        scrapperClient.deleteLink(update.message().chat().id(), new RemoveLinkRequest(update.message().text()));
        return new SendMessage(update.message().chat().id(), "Ссылка отслеживается");
    }
}
