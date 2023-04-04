package ru.tinkoff.edu.java.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.scrapper.Clients.ScrapperClient;
import ru.tinkoff.edu.java.bot.scrapper.Clients.ScrapperClientImpl;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    final ApplicationConfig config;
    private UsersService usersService;
    private ScrapperClient scrapperClient;
    private LinkTrackerImpl linkTracker = new LinkTrackerImpl();

    static final String helpText = "Введите /start, чтобы зарегистрировать пользователя\n\n" +
            "Введите /help, чтобы увидеть это сообщение вновь\n\n" +
            "Введите /list, чтобы увидеть список отслеживаемых ссылок\n\n" +
            "Введите /track, чтобы начать отслеживание ссылки\n\n" +
            "Введите /untrack, чтобы прекратить отслеживание ссылки";

    public TelegramBot(ApplicationConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Регистрация пользователя"));
        listOfCommands.add(new BotCommand("/help", "Вывод список команд"));
        listOfCommands.add(new BotCommand("/list", "Вывод списка отслеживаемых ссылок"));
        listOfCommands.add(new BotCommand("/track", "Начать отслеживание ссылки"));
        listOfCommands.add(new BotCommand("/untrack", "Прекратить отслеживание ссылки"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message messageText = update.getMessage();
            long chatId = update.getMessage().getChatId();
            switch (messageText.getText()) {
                case "/start" -> {
                    try {
                        usersService = new UsersService();
                        sendMessage(chatId, "Пользователь зарегистрирован");
                        break;
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "/help" -> {
                    try {
                        sendMessage(chatId, helpText);
                        break;
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "/track" -> {
                    try {
                        sendMessage(chatId, "Введите ссылку для отслеживания");
//                        String link = update.getMessage().getText();
//                        System.out.println(link);
//                        log.debug(link);
//                        break;
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "/untrack" -> {
                    break;
                }
                case "/list" -> {
                    break;
                }
                default -> {
                    try {
                        sendMessage(chatId, "Команды не существует");
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    private synchronized void sendMessage(long chatId, String answer) throws TelegramApiException {
        SendMessage message = new SendMessage(String.valueOf(chatId), answer);
        execute(message);
    }

    private void linkTracking(Update update) throws TelegramApiException {
        String link = update.getMessage().getText();
        if (link.contains("http://") || link.contains("https://"))
            linkTracker.addLink(link);
    }

}
