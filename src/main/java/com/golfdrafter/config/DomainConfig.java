package com.golfdrafter.config;

import com.golfdrafter.domain.FourBallMatchPlay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public FourBallMatchPlay fourBallMatchPlay() {
        return new FourBallMatchPlay();
    }
}
