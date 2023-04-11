package ru.tinkoff.edu.java.LinkParser.LinksInfo;

public record GitHubLinkInfo(String user, String repository) implements LinkInfo {

    //Метод вывода информации: пользователь-репозиторий
    @Override
    public String toString() {
        return user + "/" + repository;
    }

}
