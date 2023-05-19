package ru.tinkoff.edu.java.LinkParser.parsers;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;

@AllArgsConstructor
public class ParserChain {
    private final List<LinkParser> handlers;

    public @Nullable LinkData handle(@NotNull String link) {
        return handlers.stream()
                .map(handler -> handler.handleLink(link))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
