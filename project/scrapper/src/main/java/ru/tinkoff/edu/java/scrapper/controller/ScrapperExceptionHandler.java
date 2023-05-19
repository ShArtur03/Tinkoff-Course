package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.scrapper.DTO.controller.ApiErrorResponse;

import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice(basePackages = "ru.tinkoff.edu.java.scrapper.controller")
public class ScrapperExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    private ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildError(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "client",
                "Wrong client action"
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
