package ru.tinkoff.edu.java.LinkParser.LinksInfo;

public record StackInfo(String questionId) implements LinkInfo {

    //Метод вывода id - вопроса
    @Override
    public String toString() {
        return questionId;
    }

}
