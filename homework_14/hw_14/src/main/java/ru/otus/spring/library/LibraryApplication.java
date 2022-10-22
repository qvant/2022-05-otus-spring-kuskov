package ru.otus.spring.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableMongoRepositories
public class LibraryApplication {


    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
