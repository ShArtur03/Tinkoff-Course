package ru.tinkoff.edu.java.bot.scrapper.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@NoArgsConstructor
public class Links {
    private long id;
    private URI url;

    public Links(long id, URI url) {
        this.id = id;
        this.url = url;
    }
}