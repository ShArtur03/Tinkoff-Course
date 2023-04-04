package ru.tinkoff.edu.java.bot.DTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.DTO.response.ApiErrorResponse;

@SpringBootApplication
@RestControllerAdvice
public class BotExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleException(@NotNull MethodArgumentNotValidException e, ApiErrorResponse response) {
        return new ApiErrorResponse(
                response.description(),
                response.code(),
                response.exceptionName(),
                response.exceptionMessage(),
                response.stacktrace()
        );
    }

}
