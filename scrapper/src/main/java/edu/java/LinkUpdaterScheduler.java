package edu.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class LinkUpdaterScheduler {

  Logger logger = LoggerFactory.getLogger(LinkUpdaterScheduler.class);

  @Scheduled(fixedDelayString = "#{@scheduler.interval()}")
  public void update() {
    logger.info("Update method has called.");
  }
}
