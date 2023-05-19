package ru.tinkoff.edu.java.scrapper.DTO.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.edu.java.scrapper.DTO.entities.ChatEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private Long id;

    public Chat(ChatEntity chatEntity) {
        this.id = chatEntity.getId();
    }
}