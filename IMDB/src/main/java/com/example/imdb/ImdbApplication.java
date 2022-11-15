package com.example.imdb;

import com.example.imdb.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@AllArgsConstructor
public class ImdbApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ImdbApplication.class, args);
    }

}
