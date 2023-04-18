package ru.tinkoff.edu.java.LinkParser.Parsers;

import org.junit.Before;
import org.junit.Test;
import ru.tinkoff.edu.java.LinkParser.data.LinkData;
import ru.tinkoff.edu.java.LinkParser.parsers.GitHubLinkParser;

import static org.junit.Assert.*;

public class GitLinkParserTest {

    GitHubLinkParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new GitHubLinkParser();
    }

    @Test
    public void linkParsingValidUrl() {
        LinkData link = parser.parseLink("https://github.com/sanyarnd/tinkoff-java-course-2022/");
        assertNotNull(link);
        assertEquals("sanyarnd/tinkoff-java-course-2022", link.toString());
    }

    @Test
    public void linkParsingInvalidUrl() {
        LinkData link = null;
        try {
            link = parser.parseLink("https://github.com/sanyarnd/");
        } catch (Exception e) {
            System.err.println("Invalid URL");
        }
        assertNull(link);

    }
}