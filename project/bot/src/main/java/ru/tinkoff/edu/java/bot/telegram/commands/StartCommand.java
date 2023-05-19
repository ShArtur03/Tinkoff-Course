package ru.tinkoff.edu.java.bot.telegram.commands;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.edu.java.bot.exception.ChatAlreadyRegisteredException;
import ru.tinkoff.edu.java.bot.service.ScrapperWebService;

@Order(3)
@Component
@RequiredArgsConstructor
public class StartCommand implements Command{
    private final ScrapperWebService webService;
    private static final String COMMAND = "/start";
    private static final String welcomeMessage = "Hello!";

    @Override
    public SendMessage handle(@NotNull Message message) {
        try {
            webService.createChat(message.getChatId());
        }catch (ChatAlreadyRegisteredException ex) {
            return new SendMessage(message.getChatId().toString(), "You have already started work");
        }
        return new SendMessage(message.getChatId().toString(), welcomeMessage);
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith(COMMAND);
    }
}