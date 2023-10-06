package com.damienarroyoexemple.damienarroyoexemplebackend

import com.damienarroyoexemple.damienarroyoexemplebackend.service.StarshipService
import com.damienarroyoexemple.damienarroyoexemplebackend.service.implementation.StarshipServiceImplementation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun starshipService(): StarshipService{
        return StarshipServiceImplementation();
    }
}