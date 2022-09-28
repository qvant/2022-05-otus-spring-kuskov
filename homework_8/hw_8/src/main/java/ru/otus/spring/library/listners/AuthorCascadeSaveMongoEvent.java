package ru.otus.spring.library.listners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Book;

import java.util.List;

public class AuthorCascadeSaveMongoEvent extends AbstractMongoEventListener<Author> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Author> event) {
        Author source = event.getSource();
        List<Book> bookList = mongoOperations.findAll(Book.class);
        for (Book book : bookList
        ) {
            if (book.getAuthor().getId().equals((source).getId())) {
                book.getAuthor().setName((source).getName());
                mongoOperations.save(book);
            }
        }
    }
}
