package ru.tinkoff.edu.java.bot.telegram.commands;


import jakarta.validation.constraints.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.edu.java.bot.DTO.scrapper.LinkResponse;
import ru.tinkoff.edu.java.bot.DTO.scrapper.ListLinksResponse;
import ru.tinkoff.edu.java.bot.service.ScrapperWebService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Order(2)
@Component
public class ListCommand extends AbstractCommand{
    private final ScrapperWebService webService;

    private static final String COMMAND = "/list";
    private static final String DESCRIPTION = "Show tracking links";
    private static final String emptyListMessage =
            "You don't have tracked links yet, use /track <link> to track one";


    public ListCommand(ScrapperWebService webService) {
        super(COMMAND, DESCRIPTION);
        this.webService = webService;
    }
    @Override
    public SendMessage handle(@NotNull Message message) {
        ListLinksResponse response = webService.getAllLinks(message.getChatId());
        String text = response.size() == 0 ? emptyListMessage : getTrackedList(response);
        return new SendMessage(message.getChatId().toString(), text);
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith(COMMAND);
    }

    private String getTrackedList(ListLinksResponse response) {
        List<String> links = response.links().stream().map(LinkResponse::link).map(URI::toString).toList();
        return "List of your current tracked links: \n" + links.stream().map(link -> "- " + link).collect(Collectors.joining("\n"));
    }
}