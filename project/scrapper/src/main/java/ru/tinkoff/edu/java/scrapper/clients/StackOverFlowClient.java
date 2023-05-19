package ru.tinkoff.edu.java.scrapper.clients;

import ru.tinkoff.edu.java.LinkParser.data.StackOverFlowLinkData;
import ru.tinkoff.edu.java.scrapper.clients.DTO.StackOverFlowApiItemsResponse;

public interface StackOverFlowClient {

    StackOverFlowApiItemsResponse getQuestion(StackOverFlowLinkData question);

}
