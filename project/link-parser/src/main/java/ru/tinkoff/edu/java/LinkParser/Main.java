package ru.tinkoff.edu.java.LinkParser;

import ru.tinkoff.edu.java.linkParser.parsers.LinkParser;
import ru.tinkoff.edu.java.linkParser.parsers.ParserChain;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkParser parser = ParserChain.chain();
        String link = scanner.nextLine();
        System.out.println(parser.parseLink(link));
    }

}
