package ru.tinkoff.edu.java.bot.DTO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.DTO.request.LinkUpdate;
import ru.tinkoff.edu.java.bot.DTO.response.ApiErrorResponse;

@SpringBootApplication
@RestController
@RequestMapping("/updates")
public class BotController {
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "NOT OK")

    @PostMapping(consumes = "application/json", produces = "application/json")
    public LinkUpdate createMessage(@Valid @RequestBody LinkUpdate linkUpdate) {
        return new LinkUpdate(linkUpdate.id(),
                linkUpdate.url(),
                linkUpdate.description(),
                linkUpdate.tgChatIds());
    }
}
