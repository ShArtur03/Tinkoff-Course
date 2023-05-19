package ru.tinkoff.edu.java.LinkParser.parsers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;

public interface LinkParser {
    @Nullable LinkData handleLink(@NotNull String link);
}
