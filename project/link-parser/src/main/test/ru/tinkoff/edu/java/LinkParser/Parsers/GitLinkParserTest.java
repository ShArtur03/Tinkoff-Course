package ru.tinkoff.edu.java.LinkParser.Parsers;

import org.junit.Before;
import org.junit.Test;
import ru.tinkoff.edu.java.LinkParser.LinksInfo.LinkInfo;

import static org.junit.Assert.*;

public class GitLinkParserTest {

    GitLinkParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new GitLinkParser();
    }

    @Test
    public void linkParsingValidUrl() {
        LinkInfo link = parser.linkParsing("https://github.com/sanyarnd/tinkoff-java-course-2022/");
        assertNotNull(link);
        assertEquals("sanyarnd/tinkoff-java-course-2022", link.toString());
    }

    @Test
    public void linkParsingInvalidUrl() {
        LinkInfo link = null;
        try {
            link = parser.linkParsing("https://github.com/sanyarnd/");
        } catch (Exception e) {
            System.err.println("Invalid URL");
        }
        assertNull(link);

    }
}