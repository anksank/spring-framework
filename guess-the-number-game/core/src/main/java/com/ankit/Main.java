package com.ankit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess the number game!");

        // create context (container)
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        // returned cached instance of a singleton bean named 'numberGenerator'
        // by default, spring container defines all beans as singleton
        NumberGenerator numberGenerator = context.getBean("numberGenerator", NumberGenerator.class);

        // call next() method to get a random number
        int number = numberGenerator.next();

        // log generated number
        log.info("number: {}", number);

        // close the context - to prevent memory resource leaks
        context.close();
    }

}
