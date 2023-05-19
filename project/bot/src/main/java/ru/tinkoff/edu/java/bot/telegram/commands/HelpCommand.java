package ru.tinkoff.edu.java.bot.telegram.commands;

import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Order(1)
@Component
public class HelpCommand extends AbstractCommand{

    private static final String COMMAND = "/help";
    private static final String DESCRIPTION = "Show the list of commands";
    private final List<String> commandsDescription;

    public HelpCommand(@Lazy List<AbstractCommand> commands) {
        super(COMMAND, DESCRIPTION);
        commandsDescription = commands.stream()
                .map(c -> c.getCommand() + ": " + c.getDescription())
                .toList();
    }
    @Override
    public SendMessage handle(@NotNull Message message) {
        return new SendMessage(
                message.getChatId().toString(),
                "Description of commands: \n" + Strings.join(commandsDescription, '\n'));
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith(COMMAND);
    }

}