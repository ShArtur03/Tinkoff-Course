package ru.tinkoff.edu.java.bot.scrapper.exception;

import ru.tinkoff.edu.java.bot.DTO.response.ApiErrorResponse;

public class ApiInternalServerErrorException extends RuntimeException {

    private final ApiErrorResponse apiErrorResponse;

    public ApiInternalServerErrorException(ApiErrorResponse apiErrorResponse) {
        super("Something went wrong on Scrapper server");
        this.apiErrorResponse = apiErrorResponse;
    }

    public ApiErrorResponse getApiErrorResponse() {
        return apiErrorResponse;
    }
}