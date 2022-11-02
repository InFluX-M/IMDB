package com.example.imdb;

import com.example.imdb.file.TSVFile;
import com.example.imdb.model.Movie;
import com.example.imdb.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ImdbApplication {

    public static void main(String[] args) throws IOException {
        Person[] people = TSVFile.readPersons("C:\\Users\\feres\\Desktop\\git-pro1\\director\\data.tsv");
        for (int i = 0; i < 40; i++) {
            System.out.println(people[i]);
        }
        SpringApplication.run(ImdbApplication.class, args);
    }

}
