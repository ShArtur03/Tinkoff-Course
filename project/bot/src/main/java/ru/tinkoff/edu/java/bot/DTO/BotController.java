package ru.tinkoff.edu.java.bot.DTO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.DTO.request.LinkUpdate;

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
