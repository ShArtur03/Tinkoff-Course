package ru.tinkoff.edu.java.LinkParser.Parsers;

import org.junit.Before;
import org.junit.Test;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;
import ru.tinkoff.edu.java.LinkParser.parsers.StackOverFlowLinkParser;

import static org.junit.Assert.*;

public class StackLinkParserTest {

    StackOverFlowLinkParser parser;
    @Before
    public void setUp() throws Exception {
        parser = new StackOverFlowLinkParser();
    }

    @Test
    public void linkParsingValidUrl() {
        LinkData link = parser.parseLink("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c");
        assertNotNull(link);
        assertEquals("1642028", link.toString());
    }

    @Test
    public void linkParsingInvalidUrl() {
        LinkData link = parser.parseLink("https://stackoverflow.com/search?q=unsupported%20link");
        assertNull(link);
    }
}