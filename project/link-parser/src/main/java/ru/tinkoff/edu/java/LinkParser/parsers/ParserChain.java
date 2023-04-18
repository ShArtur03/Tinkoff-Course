package ru.tinkoff.edu.java.LinkParser.parsers;

public class ParserChain {

    public static LinkParser chain(){
        return chain(new GitHubLinkParser(), new StackOverFlowLinkParser());
    }

    public static LinkParser chain(Parser firstParser, Parser... parsers) {
        Parser previous = firstParser;
        for (Parser current : parsers) {
            previous.setParser(current);
            previous = current;
        }
        return firstParser;
    }

}
