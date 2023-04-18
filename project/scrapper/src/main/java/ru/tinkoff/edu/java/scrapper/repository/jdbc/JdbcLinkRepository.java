package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.DTO.entities.LinkEntity;

import java.sql.PreparedStatement;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcLinkRepository {

    private final JdbcTemplate template;
    private final BeanPropertyRowMapper<LinkEntity> mapper = new BeanPropertyRowMapper<>(LinkEntity.class);

    private final static String ADD_QUERY = "insert into url (link) values (?)";
    private final static String FIND_QUERY = """
            select id, link, lastCheckTime, lastUpdateTime
            from link
            where link = ?
            """;
    private final static String FIND_BY_ID_QUERY = """
            select id, link, lastCheckTime, lastUpdateTime
            from link
            where id = ?
            """;
    private final static String FIND_ALL_QUERY = "select id, link, lastCheckTime, lastUpdateTime from link";
    private final static String FIND_WITH_SUBSCRIBER_QUERY = """
            select id, link, lastCheckTime, lastUpdateTime
            from link
            join subscription s on link.id = s.linkId
            where chatId = ?
            """;
    private final static String UPDATE_LAST_CHECKED_TIME_AND_GET = """
            update link
            set lastCheckTime = now()
            where ? > lastCheckTime
            returning id, link, lastCheckTime, lastUpdateTime;
            """;
    private final static String UPDATE_LAST_UPDATE_TIME_QUERY = "update link set lastUpdateTime = ? where id = ?";
    private final static String REMOVE_QUERY = "delete from link where link = ?";
    private final static String REMOVE_BY_ID_QUERY = "delete from link where id = ?";
    private final static String REMOVE_WITH_ZERO_SUBSCRIBERS_QUERY = """
            delete from link
            where link.id in 
            (select id from link 
            left outer join subscription s on link.id = s.linkId 
            where s.chatId is NULL)
            """;

    public Long add(String link) throws DuplicateKeyException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement ps = con.prepareStatement(ADD_QUERY, new String[]{"id"});
            ps.setString(1, link);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public LinkEntity find(String link) throws EmptyResultDataAccessException {
        return template.queryForObject(FIND_QUERY, mapper, link);
    }

    public LinkEntity findById(Long id) throws EmptyResultDataAccessException {
        return template.queryForObject(FIND_BY_ID_QUERY, mapper, id);
    }

    public List<LinkEntity> findAll() {
        return template.query(FIND_ALL_QUERY, mapper);
    }

    public List<LinkEntity> findWithSubscriber(Long chatId) {
        return template.query(FIND_WITH_SUBSCRIBER_QUERY, mapper, chatId);
    }

    public List<LinkEntity> updateLastCheckedTimeAndGet(OffsetDateTime shouldBeCheckedAfter) {
        return template.query(UPDATE_LAST_CHECKED_TIME_AND_GET, mapper, shouldBeCheckedAfter);
    }

    public Integer updateLastUpdateTime(Long id, OffsetDateTime newUpdateTime) {
        log.info(newUpdateTime.toString());
        return template.update(UPDATE_LAST_UPDATE_TIME_QUERY, newUpdateTime, id);
    }

    public Integer remove(String link) {
        return template.update(REMOVE_QUERY, link);
    }

    public Integer removeById(Long id) {
        return template.update(REMOVE_BY_ID_QUERY, id);
    }

    public Integer removeWithZeroSubscribers() {
        return template.update(REMOVE_WITH_ZERO_SUBSCRIBERS_QUERY);
    }
}
