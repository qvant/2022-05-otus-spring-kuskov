package ru.otus.spring.library.listners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Genre;

import java.util.List;

public class GenreCascadeSaveMongoEvent extends AbstractMongoEventListener<Genre> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Genre> event) {
        Object source = event.getSource();
        List<Book> bookList = mongoOperations.findAll(Book.class);
        for (Book book : bookList
        ) {
            if (book.getGenre().getId().equals(((Genre) source).getId())) {
                book.getGenre().setName(((Genre) source).getName());
                mongoOperations.save(book);
            }
        }

    }
}
