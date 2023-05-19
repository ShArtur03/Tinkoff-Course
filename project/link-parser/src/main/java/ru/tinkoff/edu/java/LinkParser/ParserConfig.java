package ru.tinkoff.edu.java.LinkParser;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.LinkParser.parsers.GitHubLinkHandler;
import ru.tinkoff.edu.java.LinkParser.parsers.LinkParser;
import ru.tinkoff.edu.java.LinkParser.parsers.ParserChain;
import ru.tinkoff.edu.java.LinkParser.parsers.LinkParser;
import ru.tinkoff.edu.java.LinkParser.parsers.ParserChain;
import ru.tinkoff.edu.java.LinkParser.parsers.StackOverFlowLinkHandler;

@Configuration
public class ParserConfig {
    @Bean
    public StackOverFlowLinkHandler stackOverflowLinkHandler() {
        return new StackOverFlowLinkHandler();
    }

    @Bean
    public GitHubLinkHandler gitHubLinkHandler() {
        return new GitHubLinkHandler();
    }

    public @Bean ParserChain linkHandlerChain(List<LinkParser> handlers) {
        return new ParserChain(handlers);
    }
}
