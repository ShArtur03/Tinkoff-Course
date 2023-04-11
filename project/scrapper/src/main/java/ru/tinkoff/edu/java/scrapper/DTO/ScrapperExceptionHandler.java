package ru.tinkoff.edu.java.scrapper.DTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.tinkoff.edu.java.scrapper.DTO.response.ApiErrorResponse;

@RestControllerAdvice
public class ScrapperExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleExceptionBADREQUEST(@NotNull MethodArgumentNotValidException e, ApiErrorResponse response) {
        return new ApiErrorResponse(
                response.description(),
                response.code(),
                response.exceptionName(),
                response.exceptionMessage(),
                response.stacktrace()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleExceptionNOTFOUND(@NotNull MethodArgumentNotValidException e, ApiErrorResponse response) {
        return new ApiErrorResponse(
                response.description(),
                response.code(),
                response.exceptionName(),
                response.exceptionMessage(),
                response.stacktrace()
        );
    }

}
