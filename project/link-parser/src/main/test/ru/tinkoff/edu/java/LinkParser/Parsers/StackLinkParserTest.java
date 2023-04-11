package ru.tinkoff.edu.java.LinkParser.Parsers;

import org.junit.Before;
import org.junit.Test;
import ru.tinkoff.edu.java.LinkParser.LinksInfo.LinkInfo;

import static org.junit.Assert.*;

public class StackLinkParserTest {

    StackLinkParser parser;
    @Before
    public void setUp() throws Exception {
        parser = new StackLinkParser();
    }

    @Test
    public void linkParsingValidUrl() {
        LinkInfo link = parser.linkParsing("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c");
        assertNotNull(link);
        assertEquals("1642028", link.toString());
    }

    @Test
    public void linkParsingInvalidUrl() {
        LinkInfo link = parser.linkParsing("https://stackoverflow.com/search?q=unsupported%20link");
        assertNull(link);
    }
}