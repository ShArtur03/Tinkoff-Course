package ru.tinkoff.edu.java.bot.telegram.commands;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;
import ru.tinkoff.edu.java.LinkParser.parsers.ParserChain;
import ru.tinkoff.edu.java.bot.exception.LinkIsNotTrackingException;
import ru.tinkoff.edu.java.bot.service.ScrapperWebService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Order(5)
@Slf4j
@Component
public class UntrackCommand extends AbstractCommand{

    private final ScrapperWebService webService;

    private final ParserChain linkHandler;

    private static final String COMMAND = "/track";
    private static final String DESCRIPTION = "Start tracking links";
    private static final Pattern PATTERN = Pattern.compile("^\\s*/untrack (\\S+)\\s*$");
    private static final String linkRemovedResponse = "The link has been removed from the list of tracked links";
    private static final String wrongFormatResponse = "Use the correct input format: '\\untrack <link>'";
    private static final String wrongLinkFormatResponse =
            "You can only use GitHub repository links and StackOverflow question links";
    private static final String linkIsNotTrackingResponse = "You cannot remove an untraceable link from the list";


    public UntrackCommand(ScrapperWebService webService, ParserChain linkHandler) {
        super(COMMAND, DESCRIPTION);
        this.webService = webService;
        this.linkHandler = linkHandler;
    }

    @Override
    public SendMessage handle(@NotNull Message message) {
        Matcher matcher = PATTERN.matcher(message.getText());

        if (!matcher.matches()) {
            return new SendMessage(message.getChatId().toString(), wrongFormatResponse);
        }

        String url = matcher.group(1);
        LinkData linkData = linkHandler.handle(url);

        if (linkData == null) {
            return new SendMessage(message.getChatId().toString(), wrongLinkFormatResponse);
        }

        try {
            webService.deleteLink(message.getChatId(), url);
        } catch (LinkIsNotTrackingException ignored) {
            return new SendMessage(message.getChatId().toString(), linkIsNotTrackingResponse);
        }

        log.info("Removed link {}", url);
        return new SendMessage(message.getChatId().toString(), linkRemovedResponse);
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith(COMMAND);
    }
}
