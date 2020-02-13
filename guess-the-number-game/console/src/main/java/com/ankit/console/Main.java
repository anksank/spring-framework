package com.ankit.console;

import com.ankit.config.AppConfig;
import com.ankit.MessageGenerator;
import com.ankit.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess the number game!");

        // create context (container)
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // returned cached instance of a singleton bean named 'numberGenerator'
        // by default, spring container defines all beans as singleton
        // this works only if there is a single implementation of the NumberGenerator interface
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        // call next() method to get a random number
        int number = numberGenerator.next();

        // log generated number
        log.info("number: {}", number);

        // get messageGenerator bean from the context
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        log.info(messageGenerator.getMainMessage());
        log.info(messageGenerator.getResultMessage());

        // close the context - to prevent memory resource leaks
        context.close();
    }
}
