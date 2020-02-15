package com.ankit.config;

import com.ankit.GuessCount;
import com.ankit.MaxNumber;
import com.ankit.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // == fields ==
    @Value("${game.maxNumber:100}")
    private int maxNumber;

    @Value("${game.guessCount:8}")
    private int guessCount;

    @Value("${game.minNumber:0}")
    private int minNumber;

    // this only works in case the field name and the method name are same
    // otherwise, it throws errors

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumber() {
        return minNumber;
    }

    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }
}
