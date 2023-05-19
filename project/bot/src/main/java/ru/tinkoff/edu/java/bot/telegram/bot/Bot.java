package ru.tinkoff.edu.java.bot.telegram.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.DTO.controller.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.exception.SendingMessageException;
import ru.tinkoff.edu.java.bot.telegram.MessageHandler;
import ru.tinkoff.edu.java.bot.telegram.commands.AbstractCommand;

import javax.annotation.PostConstruct;
import java.util.List;


@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {
    private final ApplicationConfig config;
    private final List<AbstractCommand> commands;
    private final MessageHandler messageHandler;

    public Bot(ApplicationConfig config, List<AbstractCommand> commands, MessageHandler messageHandler) {
        super(config.getBot().getToken());
        this.config = config;
        this.commands = commands;
        this.messageHandler = messageHandler;
    }

    @PostConstruct
    public void init() {
        List<BotCommand> botCommands = commands.stream()
                .map(AbstractCommand::toBotCommand).toList();
        SetMyCommands setMyCommands = new SetMyCommands();
        setMyCommands.setCommands(botCommands);

        try {
            this.execute(setMyCommands);
        } catch (TelegramApiException ex) {
            log.error("Failed to set commands due to error: {}", ex.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage sendMessage;

            try {
                sendMessage = messageHandler.handle(message);
            } catch (RuntimeException ex) {
                log.error(ex.toString());
                sendMessage = new SendMessage(message.getChatId().toString(), "Sorry, an error has occurred");
            }

            try {
                execute(sendMessage);
            } catch (TelegramApiException ex) {
                log.error("Failed to set commands due to error: {}", ex.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBot().getName();
    }

    public void sendUpdates(LinkUpdateRequest updates) {
        String message = "New updates from: " + updates.url() + "\n" + updates.description();
        updates.tgChatIds().forEach(id -> sendMessage(id, message));
    }

    private void sendMessage(Long chatId, String text) {
        try {
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(chatId).text(text).build();
            this.execute(sendMessage);
        } catch (TelegramApiException ex) {
            throw new SendingMessageException(chatId, ex);
        }
    }
}