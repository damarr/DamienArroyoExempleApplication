package com.damienarroyoexemple.damienarroyoexemplebackend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class DamienArroyoExempleBackendApplication

fun main(args: Array<String>) {
    SpringApplication.run(DamienArroyoExempleBackendApplication::class.java);
}
