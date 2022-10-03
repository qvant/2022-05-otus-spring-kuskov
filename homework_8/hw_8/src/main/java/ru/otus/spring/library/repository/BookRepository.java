package ru.otus.spring.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.library.domain.Book;


public interface BookRepository extends MongoRepository<Book, String> {

}
