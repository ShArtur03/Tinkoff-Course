package ru.tinkoff.edu.java.scrapper.repository.jooq;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.DTO.model.Subscription;

@Repository
@RequiredArgsConstructor
public class JooqSubscriptionRepository {
    private final DSLContext context;
    private final ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Subscription subscription = ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Subscription.SUBSCRIPTION;

    public void add(Long chatId, Long linkId) {
        context.insertInto(subscription)
                .set(subscription.CHATID, chatId)
                .set(subscription.LINKID, linkId)
                .execute();
    }

    public List<Subscription> findAll() {
        return context.select(subscription.fields())
                .from(subscription)
                .fetchInto(Subscription.class);
    }

    public void remove(Long chatId, Long linkId) {
        context.delete(subscription)
                .where(subscription.LINKID.eq(linkId)
                        .and(subscription.CHATID.eq(chatId)))
                .execute();
    }

    public Integer countSubscriptions(Long linkId) {
        return context.fetchCount(subscription, subscription.LINKID.eq(linkId));
    }
}