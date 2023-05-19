package ru.tinkoff.edu.java.scrapper.DTO.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.edu.java.scrapper.DTO.entities.LinkEntity;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    private Long id;
    private String url;
    private OffsetDateTime lastCheckTime;
    private OffsetDateTime lastUpdateTime;
    public Link(LinkEntity linkEntity) {
        this.id = linkEntity.getId();
        this.url = linkEntity.getUrl();
        this.lastCheckTime = linkEntity.getLastCheckTime();
        this.lastUpdateTime = linkEntity.getLastUpdateTime();
    }
}