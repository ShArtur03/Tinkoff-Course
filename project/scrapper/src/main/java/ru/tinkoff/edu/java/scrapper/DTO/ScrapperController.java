package ru.tinkoff.edu.java.scrapper.DTO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.DTO.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.response.ListLinksResponse;

@SpringBootApplication
@RestController
public class ScrapperController {

    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "NOT OK")
    @ApiResponse(responseCode = "404", description = "NOT FOUND")

    @PostMapping(value = "/tg-chat/{id}", consumes = "application/json", produces = "application/json")
    public Object createChat(@RequestBody Object object) {
        return null;
    }
    @DeleteMapping(value = "/tg-chat/{id}", consumes = "application/json", produces = "application/json")
    public Object deleteChat(@RequestBody Object object) {
        return null;
    }
    @GetMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public ListLinksResponse getLink(@RequestBody ListLinksResponse listLinksResponse) {
        return new ListLinksResponse(listLinksResponse.links(), listLinksResponse.size());
    }
    @PostMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public AddLinkRequest createLink(@RequestBody AddLinkRequest addLinkRequest) {
        return new AddLinkRequest(addLinkRequest.link());
    }
    @DeleteMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public RemoveLinkRequest deleteLink(@RequestBody RemoveLinkRequest removeLinkRequest) {
        return new RemoveLinkRequest(removeLinkRequest.link());
    }
}
