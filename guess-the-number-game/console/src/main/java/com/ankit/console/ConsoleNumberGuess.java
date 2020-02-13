package com.ankit.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ConsoleNumberGuess implements ApplicationListener<ContextRefreshedEvent> {

    // == constants ==
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Container ready for use.");
    }
}
