package ru.tinkoff.edu.java.scrapper.configuration.clientConfiguration;

import ru.tinkoff.edu.java.LinkParser.LinksInfo.StackInfo;
import ru.tinkoff.edu.java.scrapper.DTO.response.StackItemsResponse;

public interface StackOverFlowClient {

    StackItemsResponse getQuestion(StackInfo link);

}
