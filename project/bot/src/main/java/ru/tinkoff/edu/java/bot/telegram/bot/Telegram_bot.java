package ru.tinkoff.edu.java.bot.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import ru.tinkoff.edu.java.bot.telegram.processor.UserMessageProcessor;
import ru.tinkoff.edu.java.bot.telegram.commands.Command;


import java.util.List;

public class Telegram_bot implements Bot{
    private final TelegramBot bot;

    private final UserMessageProcessor userMessageProcessor;


    public Telegram_bot(String token, UserMessageProcessor userMessageProcessor) {
        this.userMessageProcessor = userMessageProcessor;
        bot = new TelegramBot(token);
        bot.setUpdatesListener(this);
        bot.execute(new SetMyCommands(userMessageProcessor
                .commands()
                .stream()
                .map(Command::toApiCommand)
                .toArray(BotCommand[]::new)));
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {

    }

    @Override
    public int process(List<Update> updates) {
        for (var update : updates) {
            bot.execute(userMessageProcessor.process(update));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void start() {
    }

    @Override
    public void close() {
        bot.removeGetUpdatesListener();
    }
}