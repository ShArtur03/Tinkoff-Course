package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;


@Slf4j
public class LinkUpdaterScheduler {

    private final Logger logger = LoggerFactory.getLogger(ScrapperApplication.class);

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update(){
        logger.info("WARNING");
    }

}
