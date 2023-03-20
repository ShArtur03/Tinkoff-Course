package ru.tinkoff.edu.java.LinkParser;

import ru.tinkoff.edu.java.LinkParser.LinksInfo.LinkInfo;
import ru.tinkoff.edu.java.LinkParser.Parsers.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Parser gitParser = new GitLinkParser();
        Parser stackParser = new StackLinkParser();

        gitParser.setNextParser(stackParser);
        stackParser.setNextParser(null);

        String url = scan.nextLine();
        LinkInfo link = gitParser.linkParsing(url);
        System.out.println(link);
    }
}