package com.example.imdb.configuration;

import com.example.imdb.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    User user() {
        return new User();
    }
}
