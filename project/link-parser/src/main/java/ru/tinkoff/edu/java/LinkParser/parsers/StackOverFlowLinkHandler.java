package ru.tinkoff.edu.java.LinkParser.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;
import ru.tinkoff.edu.java.LinkParser.data.StackOverFlowLinkData;

public final class StackOverFlowLinkHandler implements LinkParser {
    private final Pattern pattern = Pattern.compile(
            "^https://stackoverflow\\.com/questions/(\\d+)/[^/]+/?$"
    );

    @Override
    public @Nullable LinkData handleLink(@NotNull String link) {
        Matcher matcher = pattern.matcher(link);
        if (!matcher.matches()) {
            return null;
        }
        Integer questionId = Integer.valueOf(matcher.group(1));
        return new StackOverFlowLinkData(questionId);
    }
}
