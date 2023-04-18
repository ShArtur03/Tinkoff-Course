package ru.tinkoff.edu.java.scrapper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.scrapper.DTO.ApiErrorResponse;

import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice(basePackages = "ru.tinkoff.edu.java.scrapper.controllers")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ApiErrorResponse> handleBadRequestException(Exception ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        "Bad request",
                        "400",
                        ex.toString(),
                        ex.getMessage(),
                        Arrays.stream(ex.getStackTrace()).map(Objects::toString).toList()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

}
