package ru.tinkoff.edu.java.bot.telegram.commands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class UnknownCommand implements Command {

    private static final String unknownCommandResponse =
            "Unknown command. Type /help to view all commands";
    @Override
    public SendMessage handle(Message message) {
        return new SendMessage(message.getChatId().toString(), unknownCommandResponse);
    }

    @Override
    public boolean supports(Message message) {
        return true;
    }
}
