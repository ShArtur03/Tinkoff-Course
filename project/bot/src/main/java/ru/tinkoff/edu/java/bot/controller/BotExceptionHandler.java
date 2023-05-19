package ru.tinkoff.edu.java.bot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.DTO.controller.ApiErrorResponse;
import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice(basePackages = "ru.tinkoff.edu.java.bot.controller")
public class BotExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {TelegramApiException.class})
    private ResponseEntity<ApiErrorResponse> handleTelegramApiException(TelegramApiException ex) {
        return buildError(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "server",
                "Error happened while sending updates"
        );
    }

    @ExceptionHandler
    protected ResponseEntity<ApiErrorResponse> handleOtherExceptions(Exception ex) {
        return buildError(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "server",
                "Error happened while sending updates"
        );
    }

    private ResponseEntity<ApiErrorResponse> buildError(Exception exception, HttpStatus httpStatus, String code, String description) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        description,
                        code,
                        exception.toString(),
                        exception.getMessage(),
                        Arrays.stream(exception.getStackTrace()).map(Objects::toString).toList()
                ),
                httpStatus
        );
    }
}
