package ru.tinkoff.edu.java.bot.telegram.commands;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

@Getter
public abstract class AbstractCommand implements Command {
    private final String command;
    private final String description;

    public AbstractCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public BotCommand toBotCommand() {
        return new BotCommand(command, description);
    }
}