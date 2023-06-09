package ru.tinkoff.edu.java.scrapper.DTO.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPk implements Serializable {
    private Long chatId;
    private Long linkId;
}