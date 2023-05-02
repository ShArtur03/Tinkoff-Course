package ru.tinkoff.edu.java.scrapper.repository.jooq;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Subscription;
import ru.tinkoff.edu.java.scrapper.DTO.model.Link;

import java.time.OffsetDateTime;
import java.util.List;

import static org.jooq.impl.DSL.currentOffsetDateTime;
import static org.jooq.impl.DSL.select;


@Repository
@RequiredArgsConstructor
public class JooqLinkRepository {
    private final DSLContext context;
    private final ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link link = ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK;
    private final Subscription subscription = Subscription.SUBSCRIPTION;

    public Link subscribe(String url, Long chatId) {
        Link link = find(url);
        if (link == null) {
            link = context.insertInto(this.link)
                    .set(this.link.LINK_, url)
                    .returning(this.link.fields())
                    .fetchOneInto(Link.class);
        }
        context.insertInto(subscription)
                .set(subscription.LINKID, link.getId())
                .set(subscription.CHATID, chatId)
                .execute();
        return link;
    }

    public @Nullable Link find(String url) {
        return context.select(link.fields())
                .from(link)
                .where(link.LINK_.eq(url))
                .fetchOneInto(Link.class);
    }

    public @Nullable Link findById(Long id) {
        return context.select(link.fields())
                .from(link)
                .where(link.ID.eq(id))
                .fetchOneInto(Link.class);
    }

    public List<Link> findAll() {
        return context.select(link.fields())
                .from(link)
                .fetchInto(Link.class);
    }

    public List<Link> findWithChatSubscription(Long chatId) {
        return context.select(link.fields())
                .from(link)
                .join(subscription).on(link.ID.eq(subscription.LINKID))
                .where(subscription.CHATID.eq(chatId))
                .fetchInto(Link.class);
    }

    public List<Link> findWithLastCheckedTimeLongAgo(OffsetDateTime shouldBeCheckedAfter) {
        return context.update(link)
                .set(link.LASTCHECKTIME, currentOffsetDateTime())
                .where(link.LASTCHECKTIME.lessThan(shouldBeCheckedAfter))
                .returning(link.fields())
                .fetchInto(Link.class);
    }

    public void updateLastUpdateTime(Long id, OffsetDateTime newUpdateTime) {
        context.update(link)
                .set(link.LASTUPDATETIME, newUpdateTime)
                .where(link.ID.eq(id))
                .execute();
    }

    public void remove(String url) {
        context.delete(link)
                .where(link.LINK_.eq(url))
                .execute();
    }

    public void removeById(Long id) {
        context.delete(link)
                .where(link.ID.eq(id))
                .execute();
    }

    public void removeWithZeroSubscriptions() {
        context.delete(link)
                .where(link.ID.in(
                        select(link.ID).from(link)
                                .leftOuterJoin(subscription)
                                .on(subscription.LINKID.eq(link.ID))
                                .where(subscription.CHATID.isNull())
                ));
    }
}