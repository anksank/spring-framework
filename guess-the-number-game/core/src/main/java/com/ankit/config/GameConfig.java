package com.ankit.config;

import com.ankit.GuessCount;
import com.ankit.MaxNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    // == fields ==
    private int maxNumber = 100;
    private int guessCount = 8;

    // this only works in case the field name and the method name are same
    // otherwise, it throws errors

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }
}
