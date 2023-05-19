package ru.tinkoff.edu.java.parsers;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;
import ru.tinkoff.edu.java.LinkParser.parsers.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LinkHandlerChainedTest {

    private final ParserChain parserChain;

    LinkHandlerChainedTest() {
        List<LinkParser> parsers = Arrays.asList(
                new GitHubLinkHandler(),
                new StackOverFlowLinkHandler()
        );
        parserChain = new ParserChain(parsers);
    }

    @Test
    void handle__incorrectLink_returnNull() {
        LinkData data = parserChain.handle("");
        assertNull(data);
    }

    @Test
    void handle__incorrectLink_returnFalse() {
        String actualLinkFirst = "https://github.com/ShArtur03";
        String actualLinkSecond = "https://github.com/ShArtur03/asd";
        String expectedLink = "https://github.com/ShArtur03/Tinkoff-Course";
        LinkData dataFalseFirst = parserChain.handle(actualLinkFirst);
        LinkData dataFalseSecond = parserChain.handle(actualLinkSecond);
        LinkData dataTrue = parserChain.handle(expectedLink);
        assertFalse(dataTrue == dataFalseFirst);
        assertFalse(dataTrue == dataFalseSecond);
    }

    @Test
    void handle__correctLink_returnTrue() {
        LinkData expectedData = parserChain.handle("https://github.com/ShArtur03/Tinkoff-Course");
        LinkData actualData = parserChain.handle("https://github.com/ShArtur03/Tinkoff-Course");
        assertEquals(expectedData, actualData);
    }
}
