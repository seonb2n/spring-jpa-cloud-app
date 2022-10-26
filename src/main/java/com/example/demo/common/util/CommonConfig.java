package com.example.demo.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

@Configuration
public class CommonConfig {

    @Bean
    public static DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

}
